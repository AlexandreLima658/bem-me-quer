package com.bem.me.quer.infra.gateways.category;

import com.bem.me.quer.infra.jpa.category.CategoryJpaEntity;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {

    static Specification<CategoryJpaEntity> hasId(final Long id) {
        return (root,
                query,
                builder
        ) -> builder.equal(root.get("id"), id);
    }
}
