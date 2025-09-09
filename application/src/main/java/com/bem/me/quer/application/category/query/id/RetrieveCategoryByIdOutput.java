package com.bem.me.quer.application.category.query.id;

import java.time.LocalDateTime;

public record RetrieveCategoryByIdOutput(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
