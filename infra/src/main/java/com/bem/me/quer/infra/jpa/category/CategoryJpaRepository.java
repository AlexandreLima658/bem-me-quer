package com.bem.me.quer.infra.jpa.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long>, JpaSpecificationExecutor<CategoryJpaEntity> {
}
