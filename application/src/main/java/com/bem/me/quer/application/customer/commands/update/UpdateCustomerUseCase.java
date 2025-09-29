package com.bem.me.quer.application.customer.commands.update;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;

import jakarta.inject.Named;

@Named
public class UpdateCustomerUseCase extends UseCase<UpdateCustomerInput, UpdateCustomerOutput> {

  private final CustomerRepository repository;

  public UpdateCustomerUseCase(final CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public UpdateCustomerOutput execute(final UpdateCustomerInput input) {

    final var id = CustomerId.from(input.id());

    final var customer = this.repository.findById(id)
        .orElseThrow(() -> NotFoundException.with(Customer.class, id));

    customer.update(input.name(), input.email(), input.address());

    this.repository.persist(customer);

    return new UpdateCustomerOutput(customer.id().value());
  }
}
