package com.bem.me.quer.infra.rest.category;


import com.bem.me.quer.application.category.commands.create.CreateCategoryInput;
import com.bem.me.quer.application.category.commands.create.CreateCategoryOutput;
import com.bem.me.quer.application.category.commands.update.UpdateCategoryOutput;
import com.bem.me.quer.application.category.query.id.RetrieveCategoryByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.ErrorInfo;
import com.bem.me.quer.infra.rest.category.models.UpdateCategoryHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/categories")
@Tag(name = "Categories", description = "categories")
public interface CategoryAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateCategoryOutput> create(@RequestBody CreateCategoryInput input);

    @PutMapping(
            value = "{categoryId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a category by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<UpdateCategoryOutput> update(
            @PathVariable(name = "categoryId") Long categoryId,
            @RequestBody UpdateCategoryHttpRequest request
    );

    @GetMapping(value = "{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve category by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category successfully recovered "),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<RetrieveCategoryByIdOutput> retrieveById(
            @PathVariable(name = "categoryId") final Long categoryId
    );

    @DeleteMapping(value = "{categoryId}")
    @Operation(summary = "Delete category by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    void delete(@PathVariable(value = "categoryId") final Long categoryId);

}
