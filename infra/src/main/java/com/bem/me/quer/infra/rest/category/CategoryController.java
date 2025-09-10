package com.bem.me.quer.infra.rest.category;


import com.bem.me.quer.application.category.commands.create.CreateCategoryInput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryOutput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryUseCase;
import com.bem.me.quer.application.category.commands.delete.DeleteCategoryUseCase;
import com.bem.me.quer.application.category.commands.update.UpdateCategoryOutput;
import com.bem.me.quer.application.category.commands.update.UpdateCategoryUseCase;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterInput;
import com.bem.me.quer.application.category.query.filter.RetrieveCategoriesByFilterOutput;
import com.bem.me.quer.application.category.query.id.RetrieveCategoryByIdOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.gateways.category.RetrieveCategoriesByFilterGatewayImpl;
import com.bem.me.quer.infra.gateways.category.RetrieveCategoryByIdGatewayImpl;
import com.bem.me.quer.infra.rest.category.models.UpdateCategoryHttpRequest;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final RetrieveCategoryByIdGatewayImpl retrieveCategoryByIdGateway;
    private final RetrieveCategoriesByFilterGatewayImpl retrieveCategoriesByFilterGateway;

    public CategoryController(
            final  CreateCategoryUseCase createCategoryUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final RetrieveCategoryByIdGatewayImpl retrieveCategoryByIdGateway,
            final RetrieveCategoriesByFilterGatewayImpl retrieveCategoriesByFilterGateway
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.retrieveCategoryByIdGateway = retrieveCategoryByIdGateway;
        this.retrieveCategoriesByFilterGateway = retrieveCategoriesByFilterGateway;
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

    @Override
    public ResponseEntity<RetrieveCategoryByIdOutput> retrieveById(final Long categoryId) {

        return ResponseEntity.ok(this.retrieveCategoryByIdGateway.execute(categoryId));
    }

    @Override
    public ResponseEntity<Pagination<RetrieveCategoriesByFilterOutput>> retrieveByFilter(
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {

        final var input = new RetrieveCategoriesByFilterInput(
                page,
                perPage,
                sort,
                direction
        );

        return ResponseEntity.ok(this.retrieveCategoriesByFilterGateway.execute(input));
    }

    @Override
    public void delete(final Long categoryId) {
        this.deleteCategoryUseCase.execute(categoryId);
    }
}
