package com.bem.me.quer.application.customer.commands.create;

public record CreateCustomerInput(
    String name,
    String email,
    String address) {
}
