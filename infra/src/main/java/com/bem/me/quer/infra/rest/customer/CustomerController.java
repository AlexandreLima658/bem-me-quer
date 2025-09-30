package com.bem.me.quer.infra.rest.customer;


import com.bem.me.quer.application.customer.commands.create.CreateCustomerInput;
import com.bem.me.quer.application.customer.commands.create.CreateCustomerOutput;
import com.bem.me.quer.application.customer.commands.create.CreateCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CustomerController  implements CustomerAPI{

    private final CreateCustomerUseCase createCustomerUseCase;

    public CustomerController(final CreateCustomerUseCase createCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
    }

    @Override
    public ResponseEntity<CreateCustomerOutput> create(final CreateCustomerInput input) {

        final var output = this.createCustomerUseCase.execute(input);

        final var uri = "/customers/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }
}
