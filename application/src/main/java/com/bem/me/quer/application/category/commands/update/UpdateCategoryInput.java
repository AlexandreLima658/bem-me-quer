package com.bem.me.quer.application.category.commands.update;

import java.time.LocalDateTime;

public record UpdateCategoryInput(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt
) {
}
