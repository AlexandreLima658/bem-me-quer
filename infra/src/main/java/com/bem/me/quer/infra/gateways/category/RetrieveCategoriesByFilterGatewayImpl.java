package com.bem.me.quer.infra.gateways.category;

import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterGateway;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterInput;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;
import com.bem.me.quer.infra.jpa.category.CategoryJpaRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RetrieveCategoriesByFilterGatewayImpl implements RetrieveCategoriesByFilterGateway {

    private final CategoryJpaRepository repository;

    public RetrieveCategoriesByFilterGatewayImpl(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<RetrieveCategoriesByFilterOutput> execute(final RetrieveCategoriesByFilterInput input) {

        final var specification = filters(input.query());

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(Sort.Direction.fromString(input.sortDirection()), input.sortBy())
        );

        final var pageResult = this.repository.findAll(specification, page);

        return new Pagination<>(
                input.page(),
                input.perPage(),
                pageResult.getTotalElements(),
                pageResult.getContent()
                        .stream()
                        .map(this::mapperFrom)
                        .toList()
        );
    }

    private RetrieveCategoriesByFilterOutput mapperFrom(final CategoryJpaEntity jpa) {
        return  new RetrieveCategoriesByFilterOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription(),
                jpa.getCreatedAt()
        );
    }

    private Specification<CategoryJpaEntity> filters(final String term) {
        return (root, query, builder) -> {

            if (Objects.isNull(term) || term.isBlank()) {
                return builder.isTrue(builder.literal(true));
            }

            return builder.like(builder.lower(root.get("name")), "%" + term.toLowerCase() + "%");
        };
    }

}
