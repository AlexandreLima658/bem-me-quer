package com.bem.me.quer.application.product.commands.update;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.product.Product;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import jakarta.inject.Named;

@Named
public class UpdateProductUseCase extends UseCase<UpdateProductInput, UpdateProductOutput> {

    private final ProductRepository repository;

    public UpdateProductUseCase(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateProductOutput execute(final UpdateProductInput input) {
        final var id = ProductId.from(input.id());

        final var product = this.repository.findById(id)
                .orElseThrow(() -> NotFoundException.with(Product.class, id));

        product.update(
                input.name(),
                input.description(),
                input.categoryId()
        );

        this.repository.persist(product);

        return new UpdateProductOutput(product.id().value());
    }
}
