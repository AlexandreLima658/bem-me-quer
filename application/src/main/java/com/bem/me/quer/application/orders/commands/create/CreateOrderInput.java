package com.bem.me.quer.application.orders.commands.create;

import com.bem.me.quer.domain.order.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record CreateOrderInput(
    Long customerId,
    LocalDateTime createdAt,
    Order.OrderStatus status,
    BigDecimal totalAmount,
    Set<Long> orderItemId
) {
}
