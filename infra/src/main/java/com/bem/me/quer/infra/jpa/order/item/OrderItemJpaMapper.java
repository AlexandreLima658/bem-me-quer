package com.bem.me.quer.infra.jpa.order.item;

import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.domain.order.item.OrderItem;
import com.bem.me.quer.domain.order.item.OrderItemFactory;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;
import com.bem.me.quer.domain.product.attributes.ProductId;
import com.bem.me.quer.infra.jpa.order.OrderJpaEntity;
import com.bem.me.quer.infra.jpa.product.ProductJpaEntity;

public interface OrderItemJpaMapper {

    static OrderItemJpaEntity toJpaEntity(final OrderItem orderItem) {

        final var product = new ProductJpaEntity()
                .setId(orderItem.productId().value());

        final var order = new OrderJpaEntity()
                .setId(orderItem.orderId().value());

        return new OrderItemJpaEntity(
                orderItem.id().value(),
                product,
                order,
                orderItem.quantity(),
                orderItem.unitPrice()
        );
    }

    static OrderItem toAggregate(final OrderItemJpaEntity jpa) {

        final var orderId = OrderId.from(jpa.getOrder().getId());
        final var orderItemId = OrderItemId.from(jpa.getId());
        final var productId = ProductId.from(jpa.getProduct().getId());

        return OrderItemFactory.create(
                orderItemId,
                orderId,
                productId,
                jpa.getQuantity(),
                jpa.getUnitPrice()

        );
    }

}
