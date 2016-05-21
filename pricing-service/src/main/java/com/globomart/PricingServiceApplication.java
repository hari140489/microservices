package com.globomart;

import com.globomart.dao.PricingRepository;
import com.globomart.dao.entity.Price;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class PricingServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final PricingRepository pricingRepository) {
        return strings -> {
            pricingRepository.save(new Price(1L, 150.0D));
            pricingRepository.save(new Price(2L, 250.0D));
            pricingRepository.save(new Price(3L, 350.0D));
            pricingRepository.save(new Price(4L, 450.0D));
            pricingRepository.save(new Price(5L, 550.0D));
            pricingRepository.save(new Price(6L, 650.0D));
            pricingRepository.save(new Price(7L, 750.0D));
            pricingRepository.save(new Price(8L, 850.0D));
        };
    }
}



