package com.globomart;

import com.globomart.vo.ProductVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@EnableCircuitBreaker
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ClientGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientGatewayApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

@RestController
class ProductCatalogApiGateWayController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProductsFallback")
    @RequestMapping(value = "products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductVo> getProducts() {
        final ParameterizedTypeReference<List<ProductVo>> ptr = new ParameterizedTypeReference<List<ProductVo>>() {
        };
        String url = "http://product-catalogue-service/products";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<ProductVo>> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ptr);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "getPriceFallback")
    @RequestMapping(value = "products/price/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrice(@RequestParam(value = "name", required = true) final String name, @RequestParam(value = "type", required = true) final String type) {
        final ParameterizedTypeReference<String> ptr = new ParameterizedTypeReference<String>() {
        };
        String url = "http://pricing-service/products/price/get";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name)
                .queryParam("type", type);
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ptr);
        return responseEntity.getBody();
    }

    public List<ProductVo> getProductsFallback() {
        return Collections.emptyList();
    }

    public String getPriceFallback(final String name, final String type) {
        return "Pricing Service down";
    }
}