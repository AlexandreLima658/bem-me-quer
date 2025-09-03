package com.bem.me.quer.domain.category;

import com.bem.me.quer.domain.category.attributes.CategoryId;

import java.time.LocalDateTime;

public interface CategoryFactory {

    static Category create(
            final CategoryId id,
            final String name,
            final String description,
            final LocalDateTime createdAt
    ) {
        return new Category(
                id,
                name,
                description,
                createdAt
        );
    }

    static Category create(
            final String name,
            final String description,
            final LocalDateTime createdAt
    ) {
        final var id = CategoryId.createNullValue();

        return new Category(
                id,
                name,
                description,
                createdAt
        );
    }
}
