package com.bem.me.quer.application.orders.commands.update;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.order.Order;
import com.bem.me.quer.domain.order.OrderRepository;
import com.bem.me.quer.domain.order.attributes.OrderId;
import jakarta.inject.Named;

@Named
public class UpdateOrderUseCase extends UseCase<UpdateOrderInput, UpdateOrderOutput> {

    private final OrderRepository orderRepository;

    public UpdateOrderUseCase(
            final OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public UpdateOrderOutput execute(final UpdateOrderInput input) {

        final var orderId = OrderId.from(input.id());

        final var order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> NotFoundException.with(Order.class, orderId));

        order.update(
                input.orderItems(),
                input.status()
        );

        this.orderRepository.persist(order);

        return new UpdateOrderOutput(order.id().value());
    }
}
