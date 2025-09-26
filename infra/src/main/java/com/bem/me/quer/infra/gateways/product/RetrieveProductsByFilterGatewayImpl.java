package com.bem.me.quer.infra.gateways.product;

import com.bem.me.quer.application.product.query.filter.RetrieveProductsByFilterGateway;
import com.bem.me.quer.application.product.query.filter.RetrieveProductsByFilterInput;
import com.bem.me.quer.application.product.query.filter.RetrieveProductsByFilterOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.jpa.product.ProductJpaEntity;
import com.bem.me.quer.infra.jpa.product.ProductJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RetrieveProductsByFilterGatewayImpl implements RetrieveProductsByFilterGateway {

    private final ProductJpaRepository repository;

    public RetrieveProductsByFilterGatewayImpl(final ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<RetrieveProductsByFilterOutput> execute(final RetrieveProductsByFilterInput input) {

        final var specification = filters(input.query());

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(Sort.Direction.fromString(input.sortDirection()), input.sortBy())
        );

        final var products = this.repository.findAll(specification, page);

        return new Pagination<>(
                input.page(),
                input.perPage(),
                products.getTotalElements(),
                products.getContent()
                        .stream()
                        .map(this::mapperFrom)
                        .toList());
    }

    private RetrieveProductsByFilterOutput mapperFrom(final ProductJpaEntity jpa) {

        return new RetrieveProductsByFilterOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription(),
                jpa.getCategory().getId()
        );
    }

    private  Specification<ProductJpaEntity> filters(final String term) {
        return (root, query, builder) -> {

            if (Objects.isNull(term) || term.isBlank()) {
                return builder.isTrue(builder.literal(true));
            }

            return builder.like(builder.lower(root.get("name")), "%" + term.toLowerCase() + "%");
        };
    }
}
