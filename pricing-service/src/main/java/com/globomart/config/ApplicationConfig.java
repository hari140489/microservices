package com.globomart.config;

import com.globomart.controller.PricingController;
import com.globomart.dao.PricingRepository;
import com.globomart.service.IPricingService;
import com.globomart.service.PricingServiceImpl;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xprk426 on 5/21/2016.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public PricingController pricingController(final RestTemplate restTemplate, final IPricingService pricingService) {
        return new PricingController(restTemplate, pricingService);
    }

    @Bean
    public IPricingService pricingService(final PricingRepository pricingRepository) {
        return new PricingServiceImpl(pricingRepository);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}
