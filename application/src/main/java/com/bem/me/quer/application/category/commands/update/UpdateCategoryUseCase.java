package com.bem.me.quer.application.category.commands.update;

import com.bem.me.quer.application.UseCase;
import com.bem.me.quer.domain.category.Category;
import com.bem.me.quer.domain.category.CategoryRepository;
import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import jakarta.inject.Named;

@Named
public class UpdateCategoryUseCase extends UseCase<UpdateCategoryInput, UpdateCategoryOutput> {

    private final CategoryRepository repository;

    public UpdateCategoryUseCase(final CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateCategoryOutput execute(final UpdateCategoryInput input) {

        final var id = CategoryId.from(input.id());

        final var category = this.repository.findById(id)
                .orElseThrow(() -> NotFoundException.with(Category.class, id));

        category.update(
                input.name(),
                input.description(),
                input.createdAt()
        );

        final var categoryId = this.repository.persist(category);

        return new UpdateCategoryOutput(categoryId.value());
    }
}
