package com.globomart.controller;

import com.globomart.controller.service.IProductService;
import com.globomart.vo.ProductVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(@RequestBody final ProductVo productVo) {
        productService.addProduct(productVo);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductVo> getProductsByType(@RequestParam(value = "type", required = false) final String type, @RequestParam(value = "name", required = false) final String name) {
        return productService.getProductsByType(type, name);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductVo> getProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable final Long id) {
        productService.deleteProduct(id);
    }
}
