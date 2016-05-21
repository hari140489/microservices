package com.globomart.dao;

import com.globomart.dao.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByTypeOrName(String type, String name);
}
