package com.bem.me.quer.infra.rest.orders;

import com.bem.me.quer.application.orders.commands.create.CreateOrderInput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderOutput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(final CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Override
    public ResponseEntity<CreateOrderOutput> create(final CreateOrderInput input) {

        final var output = createOrderUseCase.execute(input);

        final var uri = "/orders/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);

    }
}
