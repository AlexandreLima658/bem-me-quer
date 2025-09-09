package com.bem.me.quer.infra.gateways.category;

import com.bem.me.quer.application.category.query.id.RetrieveCategoryByIdGateway;
import com.bem.me.quer.application.category.query.id.RetrieveCategoryByIdOutput;
import com.bem.me.quer.domain.category.Category;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;
import com.bem.me.quer.infra.jpa.category.CategoryJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RetrieveCategoryByIdGatewayImpl implements RetrieveCategoryByIdGateway {

    private final CategoryJpaRepository repository;

    public RetrieveCategoryByIdGatewayImpl(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveCategoryByIdOutput execute(final Long id) {

        final var categoryId = CategoryId.from(id);

        return repository.findOne(CategorySpecification.hasId(categoryId.value()))
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Category.class, categoryId));

    }

    private RetrieveCategoryByIdOutput mapperFrom(final CategoryJpaEntity jpa) {
        return new RetrieveCategoryByIdOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getDescription(),
                jpa.getCreatedAt()
        );
    }

}

