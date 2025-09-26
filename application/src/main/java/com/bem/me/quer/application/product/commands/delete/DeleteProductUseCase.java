package com.bem.me.quer.application.product.commands.delete;

import com.bem.me.quer.application.UnitUseCase;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import jakarta.inject.Named;

@Named
public class DeleteProductUseCase extends UnitUseCase<Long> {

    private final ProductRepository repository;

    public DeleteProductUseCase(final ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(final Long id) {
        final var productId = ProductId.from(id);
        this.repository.deleteById(productId);
    }
}
