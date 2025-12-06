package com.bem.me.quer.domain.order;

import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public interface OrderFactory {

    static Order create(
            final OrderId orderId,
            final CustomerId customerId,
            final Set<OrderItemId> orderItemIds,
            final BigDecimal totalAmount,
            final Order.OrderStatus status,
            final LocalDateTime createdAt

    ) {
        return new Order(
                orderId,
                CustomerId.from(customerId.value()),
                orderItemIds,
                totalAmount,
                status,
                createdAt
        );
    }

    static Order create(
            final CustomerId customerId,
            final Set<OrderItemId> orderItemIds,
            final BigDecimal totalAmount,
            final Order.OrderStatus status,
            final LocalDateTime createdAt
    ) {
        final var orderId = OrderId.createNullValue();

        return new Order(
                orderId,
                CustomerId.from(customerId.value()),
                orderItemIds,
                totalAmount,
                status,
                createdAt
        );
    }
}
