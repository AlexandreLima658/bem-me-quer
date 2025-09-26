package com.bem.me.quer.domain.product;

import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.product.attributes.ProductId;

public interface ProductFactory {

    static Product create(
            final ProductId id,
            final String name,
            final String description,
            final CategoryId categoryId
    ) {
        return new Product(
                id,
                name,
                description,
                categoryId
        );
    }

    static Product create(
            final String name,
            final String description,
            final CategoryId categoryId
    ) {
        final var id = ProductId.createNullValue();

        return new Product(
                id,
                name,
                description,
                categoryId
        );
    }
}
