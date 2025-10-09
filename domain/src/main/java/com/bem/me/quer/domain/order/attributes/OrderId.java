package com.bem.me.quer.domain.order.attributes;

import com.bem.me.quer.domain.commons.attributes.Identifier;

public class OrderId extends Identifier<Long> {

    public OrderId(final Long value) {
        super(value);
    }

    public static OrderId from(final Long value) {
        return new OrderId(value);
    }

    public static OrderId createNullValue() {
        return new OrderId(null);
    }
}
