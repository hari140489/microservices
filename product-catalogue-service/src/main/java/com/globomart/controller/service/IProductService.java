package com.globomart.controller.service;

import com.globomart.vo.ProductVo;

import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
public interface IProductService {

    void addProduct(ProductVo productVo);

    List<ProductVo> getProducts();

    void deleteProduct(Long id);

    List<ProductVo> getProductsByType(String type, String name);
}
