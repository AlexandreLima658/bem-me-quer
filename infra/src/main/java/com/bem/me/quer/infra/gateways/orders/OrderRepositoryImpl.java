package com.bem.me.quer.infra.gateways.orders;

import com.bem.me.quer.domain.order.Order;
import com.bem.me.quer.domain.order.OrderRepository;
import com.bem.me.quer.domain.order.attributes.OrderId;
import com.bem.me.quer.infra.jpa.order.OrderJpaMapper;
import com.bem.me.quer.infra.jpa.order.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository repository;

    public OrderRepositoryImpl(final OrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Order> findById(final OrderId orderId) {
        return repository.findById(orderId.value())
                .map(OrderJpaMapper::toAggregate);
    }

    @Override
    public OrderId persist(final Order aggregate) {
        final var order = repository
                .save(OrderJpaMapper.toJpaEntity(aggregate));
        return OrderId.from(order.getId());
    }

    @Override
    public void deleteById(final OrderId orderId) {
        this.repository.deleteById(orderId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
