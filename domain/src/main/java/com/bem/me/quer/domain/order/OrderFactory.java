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
            final LocalDateTime orderDate,
            final Order.OrderStatus status,
            final Set<OrderItemId> orderItemIds,
            final BigDecimal totalAmount
    ) {
        return new Order(
                orderId,
                orderDate,
                status,
                CustomerId.from(customerId.value()),
                orderItemIds,
                totalAmount
        );
    }

    static Order create(
            final CustomerId customerId,
            final LocalDateTime orderDate,
            final Order.OrderStatus status,
            final Set<OrderItemId> orderItemIds,
            final BigDecimal totalAmount
    ) {
        final var orderId = OrderId.createNullValue();

        return new Order(
                orderId,
                orderDate,
                status,
                CustomerId.from(customerId.value()),
                orderItemIds,
                totalAmount
        );
    }
}
