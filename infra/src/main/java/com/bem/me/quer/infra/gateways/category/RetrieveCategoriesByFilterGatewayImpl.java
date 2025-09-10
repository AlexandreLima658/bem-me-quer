package com.bem.me.quer.infra.gateways.category;

import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterGateway;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterInput;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;
import com.bem.me.quer.infra.jpa.category.CategoryJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RetrieveCategoriesByFilterGatewayImpl implements RetrieveCategoriesByFilterGateway {

    private final CategoryJpaRepository repository;

    public RetrieveCategoriesByFilterGatewayImpl(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pagination<RetrieveCategoriesByFilterOutput> execute(final RetrieveCategoriesByFilterInput input) {

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(Sort.Direction.fromString(input.sortDirection()), input.sortBy())
        );

        Page<CategoryJpaEntity> categories = this.repository.findAll(Specification.where(null), page);

        return new Pagination<>(
                input.page(),
                input.perPage(),
                categories.getTotalElements(),
                categories.getContent()
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


}
