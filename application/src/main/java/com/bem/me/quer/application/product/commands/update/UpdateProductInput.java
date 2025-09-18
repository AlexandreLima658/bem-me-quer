package com.bem.me.quer.application.product.commands.update;

public record UpdateProductInput(
        Long id,
        String name,
        String description,
        Long categoryId
) {
}
