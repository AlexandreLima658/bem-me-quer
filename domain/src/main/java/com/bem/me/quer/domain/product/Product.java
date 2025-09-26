package com.bem.me.quer.domain.product;

import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.entities.AggregateRoot;
import com.bem.me.quer.domain.commons.exceptions.DomainException;
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

        if (name == null || name.isBlank()) {
            throw DomainException.with("Product name is required!");
        }
        if(description == null || description.isBlank()) {
            throw  DomainException.with("Product description is required!");
        }

        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }

    public void update(
       final String name,
       final String description,
       final Long categoryId
    ) {

      this.name = name;
      this.description = description;
      this.categoryId = CategoryId.from(categoryId);
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public CategoryId categoryId(){
        return categoryId;
    }


}
