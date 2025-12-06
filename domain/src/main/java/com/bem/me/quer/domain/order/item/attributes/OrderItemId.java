package com.bem.me.quer.domain.order.item.attributes;

import com.bem.me.quer.domain.commons.attributes.Identifier;

public class OrderItemId extends Identifier<Long> {

    public OrderItemId(final Long value) {
        super(value);
    }

    public static OrderItemId from(final Long value) {
        return new OrderItemId(value);
    }

    public static OrderItemId createNullValue() {
        return new OrderItemId(null);
    }
}
