package com.bem.me.quer.infra.rest.category;


import com.bem.me.quer.application.category.commands.create.CreateCategoryInput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryOutput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final  CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @Override
    public ResponseEntity<CreateCategoryOutput> create(final CreateCategoryInput input) {

        final var output = createCategoryUseCase.execute(input);

        final var uri = "/categories/" + output.id();

        return ResponseEntity.created(URI.create(uri)).body(output);
    }
}
