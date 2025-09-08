package com.bem.me.quer.domain.category;


import com.bem.me.quer.domain.category.attributes.CategoryId;
import com.bem.me.quer.domain.commons.entities.AggregateRoot;

import java.time.LocalDateTime;


public class Category extends AggregateRoot<CategoryId> {

    private String name;
    private String description;
    private LocalDateTime createdAt;

    Category(
            final CategoryId id,
            final String name,
            final String description,
            final LocalDateTime createdAt
    ){
        super(id);
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String name(){
        return name;
    }
    public String description(){
        return description;
    }
    public LocalDateTime createdAt(){
        return createdAt;
    }

    public void update(
            String name,
            String description,
            LocalDateTime createdAt
    ){
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}
