package com.bem.me.quer.domain.commons.utils;

public class Fixture {

    public static Long generateLongId(){
        return (long) (Math.random() * 1000) + 1;
    }
}
