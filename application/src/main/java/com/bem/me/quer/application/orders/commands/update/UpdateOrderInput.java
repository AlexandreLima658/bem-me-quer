package com.bem.me.quer.application.orders.commands.update;

import com.bem.me.quer.domain.order.Order;

import java.util.Set;

public record UpdateOrderInput(
        Long id,
        Set<Long> orderItems,
        Order.OrderStatus status
) {
}
