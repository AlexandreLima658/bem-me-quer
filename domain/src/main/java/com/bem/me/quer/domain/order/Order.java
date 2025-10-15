package com.bem.me.quer.domain.order;

import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class Order extends AggregateRoot<OrderId> {

    private final CustomerId customerId;
    private final Set<OrderItemId> orderItemIds;
    private final BigDecimal totalAmount;
    private final OrderStatus status;
    private final LocalDateTime createdAt;

    Order(
            final OrderId id,
            final CustomerId customerId,
            final Set<OrderItemId> orderItemIds,
            final BigDecimal totalAmount,
            final OrderStatus status,
            final LocalDateTime createdAt

            ) {
        super(id);
        this.customerId = customerId;
        this.orderItemIds = orderItemIds;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public LocalDateTime date() {
        return createdAt;
    }

    public OrderStatus status() {
        return status;
    }

    public CustomerId customerId(){
        return customerId;
    }

    public Set<OrderItemId> orderItemIds() {
        return orderItemIds;
    }

    public BigDecimal totalAmount() {
        return totalAmount;
    }

    public enum OrderStatus {
        PENDING,
        PAID,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }
}
