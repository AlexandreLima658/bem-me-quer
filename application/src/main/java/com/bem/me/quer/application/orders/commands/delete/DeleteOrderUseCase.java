package com.bem.me.quer.application.orders.commands.delete;

import com.bem.me.quer.application.UnitUseCase;
import com.bem.me.quer.domain.order.OrderRepository;
import com.bem.me.quer.domain.order.attributes.OrderId;
import jakarta.inject.Named;

@Named
public class DeleteOrderUseCase extends UnitUseCase<Long> {

    private final OrderRepository repository;

    public DeleteOrderUseCase(final OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final Long id) {
        final var orderId = OrderId.from(id);
        this.repository.deleteById(orderId);
    }
}
