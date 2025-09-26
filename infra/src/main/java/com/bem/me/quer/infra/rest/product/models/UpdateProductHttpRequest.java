package com.bem.me.quer.infra.rest.product.models;

import com.bem.me.quer.application.product.commands.update.UpdateProductInput;

public record UpdateProductHttpRequest(
        String name,
        String description,
        Long categoryId
) {

    public UpdateProductInput toInput(final Long id) {
        return new UpdateProductInput(
                id,
                name,
                description,
                categoryId
        );
    }
}
