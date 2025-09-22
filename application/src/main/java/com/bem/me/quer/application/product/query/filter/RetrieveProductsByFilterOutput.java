package com.bem.me.quer.application.product.query.filter;

public record RetrieveProductsByFilterOutput(
        Long id,
        String name,
        String description,
        Long categoryId
) {
}
