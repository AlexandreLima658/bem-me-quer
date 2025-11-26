package com.bem.me.quer.infra.rest.orders;

import com.bem.me.quer.application.orders.commands.create.CreateOrderInput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderOutput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderUseCase;
import com.bem.me.quer.application.orders.commands.update.UpdateOrderOutput;
import com.bem.me.quer.application.orders.commands.update.UpdateOrderUseCase;
import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterInput;
import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterOutput;
import com.bem.me.quer.application.orders.query.id.RetrieveOrderByIdOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.gateways.orders.RetrieveOrderByIdGatewayImpl;
import com.bem.me.quer.infra.gateways.orders.RetrieveOrdersByFilterGatewayImpl;
import com.bem.me.quer.infra.rest.orders.models.UpdateOrderHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class OrderController implements OrderAPI {

    private final CreateOrderUseCase createOrderUseCase;
    private final RetrieveOrderByIdGatewayImpl retrieveOrderByIdGateway;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final RetrieveOrdersByFilterGatewayImpl retrieveOrdersByFilterGateway;

    public OrderController(
            final CreateOrderUseCase createOrderUseCase,
            final RetrieveOrderByIdGatewayImpl retrieveOrderByIdGateway,
            final UpdateOrderUseCase updateOrderUseCase,
            final RetrieveOrdersByFilterGatewayImpl retrieveOrdersByFilterGateway
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.retrieveOrderByIdGateway = retrieveOrderByIdGateway;
        this.updateOrderUseCase = updateOrderUseCase;
        this.retrieveOrdersByFilterGateway = retrieveOrdersByFilterGateway;
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

    @Override
    public ResponseEntity<Pagination<RetrieveOrdersByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String query,
            final String direction
    ) {
        final var input =  new RetrieveOrdersByFilterInput(
                page,
                perPage,
                sort,
                query,
                direction
        );

        return ResponseEntity.ok(this.retrieveOrdersByFilterGateway.execute(input));
    }

    @Override
    public ResponseEntity<UpdateOrderOutput> update(final Long orderId, final UpdateOrderHttpRequest request) {
        final var order = request.toInput(orderId);
        return ResponseEntity.ok(this.updateOrderUseCase.execute(order));
    }
}
