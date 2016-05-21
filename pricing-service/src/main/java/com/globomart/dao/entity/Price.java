package com.globomart.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by xprk426 on 5/21/2016.
 */
@Entity
public class Price {

    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Double price;

    public Price() {
    }

    public Price(Long productId, Double price) {
        this.productId = productId;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
