package com.bem.me.quer.infra.rest.category;


import com.bem.me.quer.application.category.commands.create.CreateCategoryInput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryOutput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryUseCase;
import com.bem.me.quer.application.category.commands.update.UpdateCategoryOutput;
import com.bem.me.quer.application.category.commands.update.UpdateCategoryUseCase;
import com.bem.me.quer.infra.rest.category.models.UpdateCategoryHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    public CategoryController(
            final  CreateCategoryUseCase createCategoryUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
    }

    @Override
    public ResponseEntity<CreateCategoryOutput> create(final CreateCategoryInput input) {

        final var output = createCategoryUseCase.execute(input);

        final var uri = "/categories/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }

    @Override
    public ResponseEntity<UpdateCategoryOutput> update(final Long categoryId, final UpdateCategoryHttpRequest request) {

        final var category = request.toInput(categoryId);

        return ResponseEntity.ok(this.updateCategoryUseCase.execute(category));
    }
}
