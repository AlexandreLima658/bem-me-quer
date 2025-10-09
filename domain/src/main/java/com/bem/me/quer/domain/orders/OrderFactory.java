package com.bem.me.quer.domain.orders;

import com.bem.me.quer.domain.orders.attributes.OrderId;

import java.time.LocalDateTime;

public interface OrderFactory {

    static Orders create(
            final OrderId id,
            final LocalDateTime date,
            final Orders.Status status
    ) {
        return new Orders(id, date, status);
    }

    static Orders create(
            final LocalDateTime date,
            final Orders.Status status
    ) {
        final var id = OrderId.createNullValue();
        return new Orders(id, date, status);
    }
}
