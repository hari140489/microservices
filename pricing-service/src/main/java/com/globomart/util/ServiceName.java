package com.globomart.util;

/**
 * Created by xprk426 on 5/21/2016.
 */
public enum ServiceName {

    PRODUCT_CATALOGUE_SERVICE("product-catalogue-service");

    private String name;

    ServiceName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
