package com.bem.me.quer.domain.product;

import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.product.attributes.ProductId;

public class Product extends AggregateRoot<ProductId> {

    private String name;
    private String description;
    private CategoryId categoryId;

    Product(
            final ProductId productId,
            final String name,
            final String description,
            final CategoryId categoryId
    ) {
        super(productId);
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }


}
