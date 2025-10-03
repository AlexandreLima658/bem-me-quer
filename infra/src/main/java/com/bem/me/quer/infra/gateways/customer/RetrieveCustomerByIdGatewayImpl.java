package com.bem.me.quer.infra.gateways.customer;

import com.bem.me.quer.application.customer.query.id.RetrieveCustomerByIdGateway;
import com.bem.me.quer.application.customer.query.id.RetrieveCustomerByIdOutput;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaEntity;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RetrieveCustomerByIdGatewayImpl implements RetrieveCustomerByIdGateway {

    private final CustomerJpaRepository repository;

    public RetrieveCustomerByIdGatewayImpl(final CustomerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RetrieveCustomerByIdOutput execute(final Long id) {

        final var customerId = CustomerId.from(id);

        return this.repository.findOne(hasId(customerId.value()))
                .map(this::mapperFrom)
                .orElseThrow(() -> NotFoundException.with(Customer.class, customerId));
    }

    private RetrieveCustomerByIdOutput mapperFrom(final CustomerJpaEntity jpa) {

        return new RetrieveCustomerByIdOutput(
                jpa.getId(),
                jpa.getName(),
                jpa.getEmail(),
                jpa.getAddress()
        );
    }

    private static Specification<CustomerJpaEntity> hasId(final Long id) {
        return (
                root,
                query,
                builder
        ) -> builder.equal(root.get("id"),id);
    }


}
