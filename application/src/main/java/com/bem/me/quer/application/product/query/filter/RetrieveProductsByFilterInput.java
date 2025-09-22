package com.bem.me.quer.application.product.query.filter;

public record RetrieveProductsByFilterInput(
       int page,
       int perPage,
       String sortBy,
       String query,
       String sortDirection

) {
}
