package com.bem.me.quer.infra.rest.orders;

import com.bem.me.quer.application.orders.commands.create.CreateOrderInput;
import com.bem.me.quer.application.orders.commands.create.CreateOrderOutput;
import com.bem.me.quer.application.orders.commands.update.UpdateOrderOutput;
import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterOutput;
import com.bem.me.quer.application.orders.query.id.RetrieveOrderByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.ErrorInfo;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.rest.orders.models.UpdateOrderHttpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "orders")
@Tag(name = "Orders", description = "orders")
public interface OrderAPI {

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a new order")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Order created successfully"),
      @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
      @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
  })
  ResponseEntity<CreateOrderOutput> create(@RequestBody CreateOrderInput input);

  @GetMapping(value = "{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Retrieve order by identifier")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Order successfully recovered "),
          @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
          @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
  })

  ResponseEntity<RetrieveOrderByIdOutput> retrieveById(
          @PathVariable(name = "orderId") final Long orderId
  );

    @GetMapping
    @Operation(summary = "Retrieve a list of orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders successfully recovered"),
            @ApiResponse(responseCode = "422", description = "Validation failed",content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    ResponseEntity<Pagination<RetrieveOrdersByFilterOutput>> retrieveByFilter(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "per_page", required = false, defaultValue = "5") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "name") final String sort,
            @RequestParam(name = "query", required = false, defaultValue = "") final String query,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") final String direction
    );

  @PutMapping(
          value = "{orderId}",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  @Operation(summary = "Update a order by their identifier")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Order updated successfully"),
          @ApiResponse(responseCode = "422", description = "Validation failed", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
          @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
  })
  ResponseEntity<UpdateOrderOutput> update(
          @PathVariable(name = "orderId") Long orderId,
          @RequestBody UpdateOrderHttpRequest request
  );

}
