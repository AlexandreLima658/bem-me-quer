package com.bem.me.quer.infra.gateways.orders;

import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterGateway;
import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterInput;
import com.bem.me.quer.application.orders.query.filter.RetrieveOrdersByFilterOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.jpa.order.OrderJpaEntity;
import com.bem.me.quer.infra.jpa.order.OrderJpaRepository;
import com.bem.me.quer.infra.jpa.order.item.OrderItemJpaEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class RetrieveOrdersByFilterGatewayImpl implements RetrieveOrdersByFilterGateway {

   private final OrderJpaRepository repository;

   public RetrieveOrdersByFilterGatewayImpl(final OrderJpaRepository repository) {
    this.repository = repository;
   }

   @Override
   public Pagination<RetrieveOrdersByFilterOutput> execute(final RetrieveOrdersByFilterInput input) {

    final var specification = filters(input.query());

    final var page = PageRequest.of(
        input.page(),
        input.perPage(),
        Sort.by(Sort.Direction.fromString(input.sortDirection()), input.sortBy()));

    final var pageResult = repository.findAll(specification, page);


    return new Pagination<>(
            input.page(),
            input.perPage(),
            pageResult.getTotalElements(),
            pageResult.getContent()
                    .stream()
                    .map(this::mapperFrom)
                    .toList()
    );
   }

   private RetrieveOrdersByFilterOutput mapperFrom(final OrderJpaEntity jpa) {

       final var items = jpa.getOrderItems()
               .stream()
               .map(OrderItemJpaEntity::getId)
               .collect(Collectors.toSet());

    return new RetrieveOrdersByFilterOutput(
        jpa.getId(),
        jpa.getCustomerId().getId(),
        items,
        jpa.getStatus().name(),
        jpa.getCreatedAt()
    );
   }

   private Specification<OrderJpaEntity> filters(final String term) {
    return (root, query, builder) -> {

        if (Objects.isNull(term) || term.isBlank()) {
            return builder.isTrue(builder.literal(true));
        }

        return builder.like(root.get("id").as(String.class), "%" + term + "%");
        };
   }
}
