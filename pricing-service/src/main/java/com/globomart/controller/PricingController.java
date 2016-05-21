package com.globomart.controller;

import com.globomart.service.IPricingService;
import com.globomart.util.ServiceName;
import com.globomart.vo.ProductVo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
@RestController
@RequestMapping("products/price")
public class PricingController {

    private final IPricingService pricingService;

    private final RestTemplate restTemplate;

    public PricingController(RestTemplate restTemplate, IPricingService pricingService) {
        this.restTemplate = restTemplate;
        this.pricingService = pricingService;
    }

    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrice(@RequestParam(value = "name", required = true) final String name, @RequestParam(value = "type", required = true) final String type) {
        final ParameterizedTypeReference<List<ProductVo>> ptr = new ParameterizedTypeReference<List<ProductVo>>() {
        };
        final String url = "http://" + ServiceName.PRODUCT_CATALOGUE_SERVICE.getName() + "/products/search";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", name)
                .queryParam("type", type);
        final HttpEntity<?> entity = new HttpEntity<>(headers);
        final HttpEntity<List<ProductVo>> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, ptr);
        List<ProductVo> productVos = response.getBody();
        return pricingService.getPrice(productVos);
    }

}
