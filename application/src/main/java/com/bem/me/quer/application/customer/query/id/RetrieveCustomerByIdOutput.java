package com.bem.me.quer.application.customer.query.id;

public record RetrieveCustomerByIdOutput(
        Long id,
        String name,
        String email,
        String address
) {
}
