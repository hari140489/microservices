package com.globomart.config;

import com.globomart.controller.ProductController;
import com.globomart.controller.service.IProductService;
import com.globomart.controller.service.ProductServiceImpl;
import com.globomart.dao.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xprk426 on 5/21/2016.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public IProductService productService(final ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public ProductController productController(final IProductService productService) {
        return new ProductController(productService);
    }
}
