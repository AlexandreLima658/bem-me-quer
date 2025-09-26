package com.bem.me.quer.application.product.commands.create;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.product.ProductFactory;
import com.bem.me.quer.domain.product.ProductRepository;
import jakarta.inject.Named;

@Named
public class CreateProductUseCase extends UseCase<CreateProductInput, CreateProductOutput> {

    private final ProductRepository repository;

    public CreateProductUseCase(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateProductOutput execute(final CreateProductInput input) {

        final var categoryId = CategoryId.from(input.categoryId());

        final var product = ProductFactory.create(
                input.name(),
                input.description(),
                categoryId
        );

        final var productId = this.repository.persist(product);

        return new CreateProductOutput(productId.value());
    }
}
