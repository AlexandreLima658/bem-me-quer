package com.bem.me.quer.domain.orders;

import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.orders.attributes.OrderId;

import java.time.LocalDateTime;

public class Orders extends AggregateRoot<OrderId> {

    private final LocalDateTime date;
    private final Status status;

    Orders(
            final OrderId id,
            final LocalDateTime date,
            final Status status
    ) {
        super(id);
        this.date = date;
        this.status = status;
    }

    public LocalDateTime date() {
        return date;
    }

    public Status status() {
        return status;
    }

    public enum Status {
        ORDER_PLACED,
        PAYMENT_APPROVED,
        IN_PREPARATION,
        SHIPPED,
        DELIVERED
    }
}
