package com.bem.me.quer.infra.jpa.category;

import com.bem.me.quer.domain.category.Category;
import com.bem.me.quer.domain.category.CategoryFactory;
import com.bem.me.quer.domain.category.attributes.CategoryId;

public interface CategoryJpaMapper {

  static CategoryJpaEntity toJpaEntity(final Category category) {
    return new CategoryJpaEntity(
        category.id().value(),
        category.name(),
        category.description(),
        category.createdAt());
  }

  static Category toAggregate(final CategoryJpaEntity jpa) {

    final var categoryId = CategoryId.from(jpa.getId());

    return CategoryFactory.create(
        categoryId,
        jpa.getName(),
        jpa.getDescription(),
        jpa.getCreatedAt());
  }
}
