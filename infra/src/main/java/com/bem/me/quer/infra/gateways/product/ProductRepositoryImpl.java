package com.bem.me.quer.infra.gateways.product;

import com.bem.me.quer.domain.product.Product;
import com.bem.me.quer.domain.product.ProductRepository;
import com.bem.me.quer.domain.product.attributes.ProductId;
import com.bem.me.quer.infra.jpa.category.CategoryJpaMapper;
import com.bem.me.quer.infra.jpa.product.ProductJpaMapper;
import com.bem.me.quer.infra.jpa.product.ProductJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository repository;

    public ProductRepositoryImpl(final ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findById(final ProductId productId) {
        return repository.findById(productId.value())
                .map(ProductJpaMapper::toAggregate);
    }

    @Override
    public ProductId persist(final Product aggregate) {
        final var product = this.repository
                .save(ProductJpaMapper.toJpaEntity(aggregate));
        return ProductId.from(product.getId());
    }

    @Override
    public void deleteById(final ProductId productId) {
        this.repository.deleteById(productId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
