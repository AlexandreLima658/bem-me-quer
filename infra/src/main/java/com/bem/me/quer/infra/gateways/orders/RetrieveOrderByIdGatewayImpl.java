package com.bem.me.quer.infra.gateways.orders;

import com.bem.me.quer.application.orders.query.id.RetrieveOrderByIdGateway;
import com.bem.me.quer.application.orders.query.id.RetrieveOrderByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.order.Order;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.infra.jpa.order.OrderJpaEntity;
import com.bem.me.quer.infra.jpa.order.OrderJpaRepository;
import com.bem.me.quer.infra.jpa.order.item.OrderItemJpaEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RetrieveOrderByIdGatewayImpl implements RetrieveOrderByIdGateway {

    private final OrderJpaRepository repository;

    public RetrieveOrderByIdGatewayImpl(final OrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveOrderByIdOutput execute(final Long id) {

        final var orderId = OrderId.from(id);

        return this.repository.findOne(hasId(orderId.value()))
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Order.class, orderId));
    }

    private RetrieveOrderByIdOutput mapperFrom(final OrderJpaEntity jpa) {

        final var items = jpa.getOrderItems()
                .stream()
                .map(OrderItemJpaEntity::getId)
                .collect(Collectors.toSet());

        return new RetrieveOrderByIdOutput(
                jpa.getId(),
                jpa.getCustomerId().getId(),
                items,
                jpa.getTotalAmount(),
                jpa.getStatus().toString(),
                jpa.getCreatedAt()
        );
    }

    private static Specification<OrderJpaEntity> hasId(final Long id) {
        return (root,
                query,
                builder
        ) -> builder.equal(root.get("id"), id);
    }
}
