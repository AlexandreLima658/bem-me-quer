package com.bem.me.quer.application;

public abstract class UnitUseCase<IN> {
    protected abstract void execute(IN input);
}
