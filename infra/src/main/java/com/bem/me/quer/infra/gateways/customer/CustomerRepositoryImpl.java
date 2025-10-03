package com.bem.me.quer.infra.gateways.customer;

import com.bem.me.quer.domain.customer.Customer;
import com.bem.me.quer.domain.customer.CustomerRepository;
import com.bem.me.quer.domain.customer.attributes.CustomerId;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaMapper;
import com.bem.me.quer.infra.jpa.customer.CustomerJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository repository;

    public CustomerRepositoryImpl(final CustomerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findById(final CustomerId customerId) {
        return repository.findById(customerId.value())
                .map(CustomerJpaMapper::toAggregate);
    }

    @Override
    public CustomerId persist(final Customer aggregate) {
        final var customer = this.repository
                .save(CustomerJpaMapper.toJpaEntity(aggregate));
        return CustomerId.from(customer.getId());
    }

    @Override
    public void deleteById(final CustomerId customerId) {
        this.repository.deleteById(customerId.value());
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
