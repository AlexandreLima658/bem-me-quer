package com.bem.me.quer.application.category.query.filter;

import java.time.LocalDateTime;

public record RetrieveCategoriesByFilterOutput(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
