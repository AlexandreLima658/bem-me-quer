package com.bem.me.quer.application.customer.commands.update;

public record UpdateCustomerInput(
    Long id,
    String name,
    String email,
    String address) {
}
