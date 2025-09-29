package com.bem.me.quer.application.customer.commands.delete;

import com.bem.me.quer.application.UnitUseCase;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;

import jakarta.inject.Named;

@Named
public class DeleteCustomerUseCase extends UnitUseCase<Long> {

  private final CustomerRepository repository;

  public DeleteCustomerUseCase(final CustomerRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(final Long id) {
    final var customerId = CustomerId.from(id);
    this.repository.deleteById(customerId);
  }

}
