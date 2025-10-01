package com.bem.me.quer.application.customer.query.filter;

public record RetrieveCustomersByFilterInput(
        int page,
        int perPage,
        String query,
        String sortBy,
        String sortDirection
) {
}
