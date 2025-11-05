package com.bem.me.quer.infra.rest.orders.models;

import com.bem.me.quer.application.orders.commands.update.UpdateOrderInput;
import com.bem.me.quer.domain.order.Order;

import java.util.Set;

public record UpdateOrderHttpRequest(
        Set<Long> orderItems,
        Order.OrderStatus status
) {
    public UpdateOrderInput toInput(final Long id) {
        return new UpdateOrderInput(
                id,
                orderItems,
                status
        );
    }
}
