package com.bem.me.quer.infra.rest.customer;


import com.bem.me.quer.application.customer.commands.create.CreateCustomerInput;
import com.bem.me.quer.application.customer.commands.create.CreateCustomerOutput;
import com.bem.me.quer.application.customer.commands.create.CreateCustomerUseCase;
import com.bem.me.quer.application.customer.commands.delete.DeleteCustomerUseCase;
import com.bem.me.quer.application.customer.commands.update.UpdateCustomerOutput;
import com.bem.me.quer.application.customer.commands.update.UpdateCustomerUseCase;
import com.bem.me.quer.infra.rest.customer.models.UpdateCustomerHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CustomerController  implements CustomerAPI{

    private final CreateCustomerUseCase createCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;
    private final DeleteCustomerUseCase deleteCustomerUseCase;

    public CustomerController(
            final CreateCustomerUseCase createCustomerUseCase,
            final UpdateCustomerUseCase updateCustomerUseCase,
            final DeleteCustomerUseCase deleteCustomerUseCase
    ) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerUseCase = deleteCustomerUseCase;
    }

    @Override
    public ResponseEntity<CreateCustomerOutput> create(final CreateCustomerInput input) {

        final var output = this.createCustomerUseCase.execute(input);

        final var uri = "/customers/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }

    @Override
    public ResponseEntity<UpdateCustomerOutput> update(final Long customerId, final UpdateCustomerHttpRequest request) {
        final var customer = request.toInput(customerId);
        return ResponseEntity.ok(this.updateCustomerUseCase.execute(customer));
    }

    @Override
    public void delete(final Long customerId) {
        this.deleteCustomerUseCase.execute(customerId);
    }
}
