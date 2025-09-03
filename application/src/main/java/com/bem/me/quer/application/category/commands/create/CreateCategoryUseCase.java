package com.bem.me.quer.application.category.commands.create;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.category.CategoryFactory;
import com.bem.me.quer.domain.category.CategoryRepository;
import jakarta.inject.Named;

@Named
public class CreateCategoryUseCase extends UseCase<CreateCategoryInput, CreateCategoryOutput> {

    private final CategoryRepository repository;

    public CreateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryInput input) {

        final var category = CategoryFactory.create(
                input.name(),
                input.description(),
                input.createdAt()
        );

        final var categoryId = this.repository.persist(category);

        return new CreateCategoryOutput(categoryId.value());
    }
}
