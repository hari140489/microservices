package com.globomart.controller.service;

import com.globomart.dao.ProductRepository;
import com.globomart.dao.entity.Product;
import com.globomart.vo.ProductVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductVo productVo) {
        final Product product = new Product();
        product.setName(productVo.getName());
        product.setType(productVo.getType());
        productRepository.save(product);
    }

    @Override
    public List<ProductVo> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        return convertProducts(products);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }

    @Override
    public List<ProductVo> getProductsByTypeAndName(String type, String name) {
        List<Product> products = productRepository.findByTypeAndName(type, name);
        return convertProducts(products);
    }

    @Override
    public List<ProductVo> getProductsByType(String type) {
        List<Product> products = productRepository.findByType(type);
        return convertProducts(products);
    }

    private List<ProductVo> convertProducts(Iterable<Product> products) {
        final List<ProductVo> productVos = new ArrayList<>();
        ProductVo productVo;
        for (Product product : products) {
            productVo = new ProductVo();
            productVo.setId(product.getId());
            productVo.setName(product.getName());
            productVo.setType(product.getType());
            productVos.add(productVo);
        }
        return productVos;
    }
}
