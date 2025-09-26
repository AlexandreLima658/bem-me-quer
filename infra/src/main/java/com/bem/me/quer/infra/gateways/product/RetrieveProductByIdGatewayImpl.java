package com.bem.me.quer.infra.gateways.product;

import com.bem.me.quer.application.product.query.id.RetrieveProductByIdGateway;
import com.bem.me.quer.application.product.query.id.RetrieveProductByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.product.Product;
import com.bem.me.quer.domain.product.attributes.ProductId;
import com.bem.me.quer.infra.jpa.product.ProductJpaEntity;
import com.bem.me.quer.infra.jpa.product.ProductJpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RetrieveProductByIdGatewayImpl implements RetrieveProductByIdGateway {

    private final ProductJpaRepository repository;

    public RetrieveProductByIdGatewayImpl(final ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveProductByIdOutput execute(final Long id) {
        final var productId = ProductId.from(id);

        return this.repository.findOne(hasId(productId.value()))
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Product.class, productId));
    }

    private RetrieveProductByIdOutput mapperFrom(final ProductJpaEntity jpa) {

        return new RetrieveProductByIdOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription(),
                jpa.getCategory().getId()
        );
    }

    private static Specification<ProductJpaEntity> hasId(final Long id) {
        return (root,
                query,
                builder
        ) -> builder.equal(root.get("id"), id);
    }
}
