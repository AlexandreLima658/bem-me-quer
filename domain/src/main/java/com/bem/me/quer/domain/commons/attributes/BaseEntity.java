package com.bem.me.quer.domain.commons.attributes;

public abstract class BaseEntity<Id extends Identifier<?>> {

    private final Id id;

    protected BaseEntity(final Id id) {
        this.id = id;
    }

    public Id id(){
        return id;
    }
}
