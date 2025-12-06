package com.bem.me.quer.application.orders.query.filter;

public record RetrieveOrdersByFilterInput(
        int page,
        int perPage,
        String sortBy,
        String query,
        String sortDirection
) {
}
