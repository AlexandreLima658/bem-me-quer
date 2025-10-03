package com.bem.me.quer.infra.jpa.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long>, JpaSpecificationExecutor<CustomerJpaEntity> {
}
