package com.bem.me.quer.infra.jpa.product;

import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.product.Product;
import com.bem.me.quer.domain.product.ProductFactory;
import com.bem.me.quer.domain.product.attributes.ProductId;
import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;

public interface ProductJpaMapper {

    static ProductJpaEntity toJpaEntity(final Product product) {

        final var category = new CategoryJpaEntity()
                .setId(product.categoryId().value());

        return new ProductJpaEntity(
                product.id().value(),
                product.name(),
                product.description(),
                category
        );
    }

    static Product toAggregate(final ProductJpaEntity jpa) {

        final var id = ProductId.from(jpa.getId());

        final var categoryId = CategoryId.from(jpa.getCategory().getId());

        return ProductFactory.create(
                id,
                jpa.getName(),
                jpa.getDescription(),
                categoryId
        );
    }
}
