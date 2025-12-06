package com.bem.me.quer.application.orders.commands.create;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.domain.order.OrderFactory;
import com.bem.me.quer.domain.order.OrderRepository;
import com.bem.me.quer.domain.order.item.attributes.OrderItemId;
import jakarta.inject.Named;

import java.util.stream.Collectors;

@Named
public class CreateOrderUseCase extends UseCase<CreateOrderInput, CreateOrderOutput> {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public CreateOrderUseCase(
            final OrderRepository orderRepository,
            final CustomerRepository customerRepository

    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;

    }

    @Override
    public CreateOrderOutput execute(final CreateOrderInput input) {

        final var customerId = CustomerId.from(input.customerId());

        final var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> NotFoundException.with(Customer.class, customerId));

        final var orderItems = input.orderItemId()
                .stream()
                .map(OrderItemId::from)
                .collect(Collectors.toSet());

        final var order = OrderFactory.create(
                customer.id(),
                orderItems,
                input.totalAmount(),
                input.status(),
                input.createdAt()
        );

        final var id = this.orderRepository.persist(order);

        return new CreateOrderOutput(id.value());
    }

}
