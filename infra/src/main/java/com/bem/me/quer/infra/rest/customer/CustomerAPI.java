package com.bem.me.quer.infra.rest.customer;


import com.bem.me.quer.application.customer.commands.create.CreateCustomerInput;
import com.bem.me.quer.application.customer.commands.create.CreateCustomerOutput;
import com.bem.me.quer.application.customer.commands.update.UpdateCustomerOutput;
import com.bem.me.quer.application.customer.query.filter.RetrieveCustomersByFilterOutput;
import com.bem.me.quer.application.customer.query.id.RetrieveCustomerByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.ErrorInfo;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.rest.customer.models.UpdateCustomerHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/customers")
@Tag(name = "Customers", description = "customers")
public interface CustomerAPI {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<CreateCustomerOutput> create(@RequestBody CreateCustomerInput input);

    @PutMapping(
            value = "{customerId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Update a customer by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })

    ResponseEntity<UpdateCustomerOutput> update(
            @PathVariable(name = "customerId") Long customerId,
            @RequestBody UpdateCustomerHttpRequest request
    );

    @DeleteMapping(value = "{customerId}")
    @Operation(summary = "Delete customer by their identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    void delete(@PathVariable(value = "customerId") final Long customerId);

    @GetMapping
    @Operation(summary = "Retrieve a list of customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers successfully recovered"),
            @ApiResponse(responseCode = "422", description = "Validation failed",content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<Pagination<RetrieveCustomersByFilterOutput>> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "q", required = false, defaultValue = "") final String query,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(value = "{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieve customer by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer successfully recovered "),
            @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })

    ResponseEntity<RetrieveCustomerByIdOutput> retrieveById(
            @PathVariable(name = "customerId") final Long customerId
    );

}
