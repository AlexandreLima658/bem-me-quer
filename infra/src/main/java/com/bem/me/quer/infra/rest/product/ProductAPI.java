package com.bem.me.quer.infra.rest.product;

import com.bem.me.quer.application.product.commands.create.CreateProductInput;
import com.bem.me.quer.application.product.commands.create.CreateProductOutput;
import com.bem.me.quer.application.product.commands.update.UpdateProductOutput;
import com.bem.me.quer.application.product.query.filter.RetrieveProductsByFilterOutput;
import com.bem.me.quer.application.product.query.id.RetrieveProductByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.ErrorInfo;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.rest.product.models.UpdateProductHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/products")
@Tag(name = "Products", description = "products")
public interface ProductAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateProductOutput> create(@RequestBody CreateProductInput input);

    @PutMapping(
            value = "{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a product by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })

    ResponseEntity<UpdateProductOutput> update(
            @PathVariable(name = "productId") Long productId,
            @RequestBody UpdateProductHttpRequest request
    );

    @DeleteMapping(value = "{productId}")
    @Operation(summary = "Delete product by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    void delete(@PathVariable(value = "productId") final Long productId);

    @GetMapping(value = "{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve product by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product successfully recovered "),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })

    ResponseEntity<RetrieveProductByIdOutput> retrieveById(
            @PathVariable(name = "productId") final Long productId
    );

    @GetMapping
    @Operation(summary = "Retrieve a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products successfully recovered"),
            @ApiResponse(responseCode = "422", description = "Validation failed",content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<Pagination<RetrieveProductsByFilterOutput>> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "query", required = false, defaultValue = "") final String query,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );



}
