package com.globomart.dao;

import com.globomart.dao.entity.Price;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by xprk426 on 5/21/2016.
 */
public interface PricingRepository extends CrudRepository<Price, Long> {
    Price findByProductId(Long id);
}
