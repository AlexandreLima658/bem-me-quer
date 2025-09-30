package com.bem.me.quer.infra.jpa.customer;

import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.CustomerFactory;
import com.bem.me.quer.domain.customer.attributes.CustomerId;

public interface CustomerJpaMapper {

  static CustomerJpaEntity toJpaEntity(final Customer customer) {
    return new CustomerJpaEntity(
        customer.id().value(),
        customer.name(),
        customer.email(),
        customer.address());
  }

  static Customer toAggregate(final CustomerJpaEntity jpa) {

    final var customerId = CustomerId.from(jpa.getId());

    return CustomerFactory.create(
        customerId,
        jpa.getName(),
        jpa.getEmail(),
        jpa.getAddress());
  }
}
