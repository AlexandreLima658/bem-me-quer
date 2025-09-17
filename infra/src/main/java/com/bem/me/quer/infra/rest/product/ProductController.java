package com.bem.me.quer.infra.rest.product;

import com.bem.me.quer.application.product.commands.create.CreateProductInput;
import com.bem.me.quer.application.product.commands.create.CreateProductOutput;
import com.bem.me.quer.application.product.commands.create.CreateProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;

    public ProductController(final CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Override
    public ResponseEntity<CreateProductOutput> create(final CreateProductInput input) {

        final var output = createProductUseCase.execute(input);

        final var uri = "/products/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }
}
