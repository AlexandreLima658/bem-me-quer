package com.bem.me.quer.domain.customer;


import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.commons.exceptions.DomainException;
import com.bem.me.quer.domain.customer.attributes.CustomerId;

public class Customer extends AggregateRoot<CustomerId> {

  private String name;
  private String email;
  private String address;

  Customer(
      final CustomerId id,
      final String name,
      final String email,
      final String address) {
    super(id);

    if (name == null || name.isBlank()) {
      throw DomainException.with("Customer name is required!");
    }

    this.name = name;
    this.email = email;
    this.address = address;
  }

  public void update(
      final String name,
      final String email,
      final String address) {

    this.name = name;
    this.email = email;
    this.address = address;
  }

  public String name() {
    return name;
  }

  public String email() {
    return email;
  }

  public String address() {
    return address;
  }
}
