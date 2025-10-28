package com.bem.me.quer.infra.jpa.order;

import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.domain.order.Order;
import com.bem.me.quer.domain.order.OrderFactory;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaEntity;
import com.bem.me.quer.infra.jpa.order.item.OrderItemJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public interface OrderJpaMapper {

    static OrderJpaEntity toJpaEntity(final Order order) {

        final var customerId = new CustomerJpaEntity()
                .setId(order.customerId().value());

        final var items = order.orderItemIds()
                .stream()
                .map(item -> new OrderItemJpaEntity().setId(item.value()))
                .collect(Collectors.toSet());

        return new OrderJpaEntity(
                order.id().value(),
                customerId,
                items,
                order.totalAmount(),
                order.status(),
                order.createdAt()
        );

    }

    static Order toAggregate(final OrderJpaEntity jpa) {

        final var orderId = OrderId.from(jpa.getId());
        final var customerId = CustomerId.from(jpa.getCustomerId().getId());

        final var  items = jpa.getOrderItems()
                .stream()
                .map(item -> new OrderItemId(item.getId()))
                .collect(Collectors.toSet());

        return OrderFactory.create(
                orderId,
                customerId,
                items,
                jpa.getTotalAmount(),
                jpa.getStatus(),
                jpa.getCreatedAt()
        );
    }
}
