package com.bem.me.quer.infra.rest.orders;

import com.bem.me.quer.application.orders.commands.create.CreateOrderInput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderOutput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderUseCase;
import com.bem.me.quer.application.orders.query.id.RetrieveOrderByIdOutput;
import com.bem.me.quer.infra.gateways.orders.RetrieveOrderByIdGatewayImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCase createOrderUseCase;
    private final RetrieveOrderByIdGatewayImpl retrieveOrderByIdGateway;

    public OrderController(
            final CreateOrderUseCase createOrderUseCase,
            final RetrieveOrderByIdGatewayImpl retrieveOrderByIdGateway
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.retrieveOrderByIdGateway = retrieveOrderByIdGateway;
    }

    @Override
    public ResponseEntity<CreateOrderOutput> create(final CreateOrderInput input) {

        final var output = createOrderUseCase.execute(input);

        final var uri = "/orders/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);

    }

    @Override
    public ResponseEntity<RetrieveOrderByIdOutput> retrieveById(final Long orderId) {
        return ResponseEntity.ok(this.retrieveOrderByIdGateway.execute(orderId));
    }
}
