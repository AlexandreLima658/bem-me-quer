package com.bem.me.quer.infra.gateways.customer;


import com.bem.me.quer.application.customer.query.filter.RetrieveCustomersByFilterGateway;
import com.bem.me.quer.application.customer.query.filter.RetrieveCustomersByFilterInput;
import com.bem.me.quer.application.customer.query.filter.RetrieveCustomersByFilterOutput;
import com.bem.me.quer.domain.pagination.Pagination;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaEntity;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RetrieveCustomersByFilterGatewayImpl implements RetrieveCustomersByFilterGateway {

    private final CustomerJpaRepository repository;

    public RetrieveCustomersByFilterGatewayImpl(final CustomerJpaRepository repository) {
        this.repository = repository;
    }


    @Override
    public Pagination<RetrieveCustomersByFilterOutput> execute(RetrieveCustomersByFilterInput input) {

        final var specification = filters(input.query());

        final var page = PageRequest.of(
                input.page(),
                input.perPage(),
                Sort.by(Sort.Direction.fromString(input.sortDirection()), input.sortBy())
        );

        final var pageResult = this.repository.findAll(specification, page);

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

    private RetrieveCustomersByFilterOutput mapperFrom(final CustomerJpaEntity jpa) {
        return new RetrieveCustomersByFilterOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getEmail(),
                jpa.getAddress()
        );
    }

    private Specification<CustomerJpaEntity> filters(final String term) {
        return ((root, query, builder) -> {

            if (Objects.isNull(term) || term.isBlank()) {
                return builder.isTrue((builder.literal(true)));
            }

            return builder.like(builder.lower(root.get("name")), "%" + term.toLowerCase() + "%");
        });
    }
}
