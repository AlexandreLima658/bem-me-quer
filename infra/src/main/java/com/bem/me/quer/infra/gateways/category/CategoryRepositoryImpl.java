package com.bem.me.quer.infra.gateways.category;

import com.bem.me.quer.domain.category.Category;
import com.bem.me.quer.domain.category.CategoryRepository;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.infra.jpa.category.CategoryJpaMapper;
import com.bem.me.quer.infra.jpa.category.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository repository;

    public CategoryRepositoryImpl(final CategoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Category> findById(final CategoryId categoryId) {
        return repository.findById(categoryId.value())
                .map(CategoryJpaMapper::toAggregate);
    }

    @Override
    public CategoryId persist(final Category aggregate) {
        final var category = repository
                .save(CategoryJpaMapper.toJpaEntity(aggregate));
        return CategoryId.from(category.getId());
    }

    @Override
    public void deleteById(final CategoryId categoryId) {
        this.repository.deleteById(categoryId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
