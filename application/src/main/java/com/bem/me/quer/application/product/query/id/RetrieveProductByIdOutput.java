package com.bem.me.quer.application.product.query.id;

public record RetrieveProductByIdOutput(
        Long id,
        String name,
        String description,
        Long categoryId
) {
}
