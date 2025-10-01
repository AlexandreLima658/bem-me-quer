package com.bem.me.quer.application.customer.query.filter;

public record RetrieveCustomersByFilterOutput(
        Long id,
        String name,
        String email,
        String address
) {
}
