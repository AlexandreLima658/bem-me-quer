package com.bem.me.quer.infra.rest.product;

import com.bem.me.quer.application.product.commands.create.CreateProductInput;
import com.bem.me.quer.application.product.commands.create.CreateProductOutput;
import com.bem.me.quer.application.product.commands.create.CreateProductUseCase;
import com.bem.me.quer.application.product.commands.update.UpdateProductOutput;
import com.bem.me.quer.application.product.commands.update.UpdateProductUseCase;
import com.bem.me.quer.infra.rest.product.models.UpdateProductHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final UpdateProductUseCase updateProductUseCase
    ) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @Override
    public ResponseEntity<CreateProductOutput> create(final CreateProductInput input) {

        final var output = createProductUseCase.execute(input);

        final var uri = "/products/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }

    @Override
    public ResponseEntity<UpdateProductOutput> update(final Long productId, final UpdateProductHttpRequest request) {

        final var product = request.toInput(productId);

        return ResponseEntity.ok(this.updateProductUseCase.execute(product));
    }
}
