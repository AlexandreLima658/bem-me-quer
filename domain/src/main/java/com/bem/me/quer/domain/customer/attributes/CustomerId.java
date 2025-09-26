package com.bem.me.quer.domain.customer.attributes;

import com.bem.me.quer.domain.commons.attributes.Identifier;

public class CustomerId extends Identifier<Long> {

  public CustomerId(final Long value) {
    super(value);
  }

  public static CustomerId from(final Long value) {
    return new CustomerId(value);
  }

  public static CustomerId createNullValue() {
    return new CustomerId(null);
  }
}
