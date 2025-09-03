package com.bem.me.quer.domain.commons.attributes;

public abstract class Identifier<T>{

    private final T value;

    protected Identifier(final T value) {
        this.value = value;
    }

    public T value(){
        return  value;
    }
}
