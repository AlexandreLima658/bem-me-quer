package com.bem.me.quer.application.customer.commands.create;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.customer.CustomerFactory;
import com.bem.me.quer.domain.customer.CustomerRepository;

import jakarta.inject.Named;

@Named
public class CreateCustomerUseCase extends UseCase<CreateCustomerInput, CreateCustomerOutput> {

  private final CustomerRepository repository;

  public CreateCustomerUseCase(final CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public CreateCustomerOutput execute(final CreateCustomerInput input) {

    final var customer = CustomerFactory.create(input.name(), input.email(), input.address());

    final var id = this.repository.persist(customer);

    return new CreateCustomerOutput(id.value());
  }
}
