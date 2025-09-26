package com.bem.me.quer.domain.product.attributes;

import com.bem.me.quer.domain.commons.attributes.Identifier;

public class ProductId extends Identifier<Long> {

    public ProductId(final Long value) {
        super(value);
    }

    public static ProductId from(final Long value) {
        return new ProductId(value);
    }

    public static ProductId createNullValue() {
        return new ProductId(null);
    }


}
