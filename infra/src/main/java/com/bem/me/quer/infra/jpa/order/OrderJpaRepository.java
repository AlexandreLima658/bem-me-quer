package com.bem.me.quer.infra.jpa.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long>, JpaSpecificationExecutor<OrderJpaEntity> {
}
