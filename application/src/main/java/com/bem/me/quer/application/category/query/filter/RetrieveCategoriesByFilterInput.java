package com.bem.me.quer.application.category.query.filter;

public record RetrieveCategoriesByFilterInput(
        int page,
        int perPage,
        String sortBy,
        String query,
        String sortDirection

) {
}
