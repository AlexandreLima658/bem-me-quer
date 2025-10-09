package com.bem.me.quer.domain.order.item;

import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;
import com.bem.me.quer.domain.product.attributes.ProductId;

import java.math.BigDecimal;

public interface OrderItemFactory {

    static OrderItem create(
            final OrderItemId orderItemId,
            final OrderId orderId,
            final ProductId productId,
            final Integer quantity,
            final BigDecimal unitPrice
    ) {
        return new OrderItem(
                orderItemId,
                orderId,
                productId,
                quantity,
                unitPrice

        );
    }

    static OrderItem create(

            final OrderId orderId,
            final ProductId productId,
            final Integer quantity,
            final BigDecimal unitPrice
    ) {
        final var orderItemId = OrderItemId.createNullValue();

        return new OrderItem(
                orderItemId,
                orderId,
                productId,
                quantity,
                unitPrice

        );
    }
}
