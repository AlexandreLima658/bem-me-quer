package com.bem.me.quer.infra.rest.category.models;

import com.bem.me.quer.application.category.commands.update.UpdateCategoryInput;

import java.time.LocalDateTime;

public record UpdateCategoryHttpRequest(
        String name,
        String description,
        LocalDateTime createdAt
) {

    public UpdateCategoryInput toInput(final Long id) {
        return new UpdateCategoryInput(
                id,
                name,
                description,
                createdAt
        );
    }
}
