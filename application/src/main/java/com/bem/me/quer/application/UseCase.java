package com.bem.me.quer.application;

public abstract class UseCase<IN, OUT> {
    protected abstract OUT execute(IN input);
}
