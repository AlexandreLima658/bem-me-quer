package com.bem.me.quer.domain.customer;

import com.bem.me.quer.domain.customer.attributes.CustomerId;

public interface CustomerFactory {

  static Customer create(
      final CustomerId id,
      final String name,
      final String email,
      final String address) {

    return new Customer(
        id,
        name,
        email,
        address);
  }

  static Customer create(
      final String name,
      final String email,
      final String address) {

    final var id = CustomerId.createNullValue();

    return new Customer(
        id,
        name,
        email,
        address);
  }
}
