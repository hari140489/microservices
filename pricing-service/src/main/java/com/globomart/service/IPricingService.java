package com.globomart.service;

import com.globomart.vo.ProductVo;

import java.util.Collection;
import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
public interface IPricingService {

    String getPrice(List<ProductVo> productVos);
}
