package com.globomart.service;

import com.globomart.dao.PricingRepository;
import com.globomart.dao.entity.Price;
import com.globomart.vo.ProductVo;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by xprk426 on 5/21/2016.
 */
public class PricingServiceImpl implements IPricingService {

    public static final String NO_PRODUCT_FOUND = "No Product Found";
    private final PricingRepository pricingRepository;

    public PricingServiceImpl(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    @Override
    public String getPrice(final List<ProductVo> productVos) {
        if (CollectionUtils.isEmpty(productVos)) {
            return NO_PRODUCT_FOUND;
        }
        ProductVo productVo = productVos.get(0);
        final Price price = pricingRepository.findByProductId(productVo.getId());
        return price == null ? NO_PRODUCT_FOUND : price.getPrice() == null ? NO_PRODUCT_FOUND : price.getPrice().toString();
    }
}
