package com.bem.me.quer.application.category.commands.create;

import java.time.LocalDateTime;

public record CreateCategoryInput(
        String name,
        String description,
        LocalDateTime createdAt
) {
}
