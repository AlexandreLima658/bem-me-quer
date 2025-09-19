package com.bem.me.quer.infra.rest.product;

import com.bem.me.quer.application.product.commands.create.CreateProductInput;
import com.bem.me.quer.application.product.commands.create.CreateProductOutput;
import com.bem.me.quer.application.product.commands.create.CreateProductUseCase;
import com.bem.me.quer.application.product.commands.delete.DeleteProductUseCase;
import com.bem.me.quer.application.product.commands.update.UpdateProductOutput;
import com.bem.me.quer.application.product.commands.update.UpdateProductUseCase;
import com.bem.me.quer.application.product.query.id.RetrieveProductByIdOutput;
import com.bem.me.quer.infra.gateways.product.RetrieveProductByIdGatewayImpl;
import com.bem.me.quer.infra.rest.product.models.UpdateProductHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class ProductController implements ProductAPI {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final RetrieveProductByIdGatewayImpl retrieveProductByIdGateway;

    public ProductController(
            final CreateProductUseCase createProductUseCase,
            final UpdateProductUseCase updateProductUseCase,
            final DeleteProductUseCase deleteProductUseCase,
            final RetrieveProductByIdGatewayImpl retrieveProductByIdGateway
    ) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.retrieveProductByIdGateway = retrieveProductByIdGateway;
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

    @Override
    public void delete(final Long productId) {
        this.deleteProductUseCase.execute(productId);
    }

    @Override
    public ResponseEntity<RetrieveProductByIdOutput> retrieveById(final Long productId) {
        final var output = this.retrieveProductByIdGateway.execute(productId);
        return ResponseEntity.ok(output);
    }
}
