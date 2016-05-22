package com.globomart.controller;

import com.globomart.service.IPricingService;
import com.globomart.util.ServiceName;
import com.globomart.vo.ProductVo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

/**
 * Created by xprk426 on 5/21/2016.
 */
@RestController
@RequestMapping("products/price")
public class PricingController {

    private final IPricingService pricingService;

    private final LoadBalancerClient loadBalancerClient;

    public PricingController(LoadBalancerClient loadBalancerClient, IPricingService pricingService) {
        this.loadBalancerClient = loadBalancerClient;
        this.pricingService = pricingService;
    }

    @RequestMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPrice(@RequestParam(value = "name", required = true) final String name, @RequestParam(value = "type", required = true) final String type) {
        ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceName.PRODUCT_CATALOGUE_SERVICE.getName());
        URI uri = serviceInstance.getUri();
        String url = uri.toString() + "/products/search?name=" + name + "&type=" + type;
        ProductVo[] productVos = (new RestTemplate()).getForObject(url, ProductVo[].class);
        return pricingService.getPrice(Arrays.asList(productVos));
    }

}
