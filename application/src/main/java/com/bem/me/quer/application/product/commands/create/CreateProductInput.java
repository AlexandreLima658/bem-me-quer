package com.bem.me.quer.application.product.commands.create;

public record CreateProductInput(
        String name,
        String description,
        Long categoryId
) {
}
