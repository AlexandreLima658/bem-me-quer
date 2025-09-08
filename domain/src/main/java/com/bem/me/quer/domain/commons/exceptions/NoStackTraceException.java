package com.bem.me.quer.domain.commons.exceptions;

public class NoStackTraceException extends RuntimeException {

    public NoStackTraceException(final String message) {
        super(message, null);
    }

    public NoStackTraceException(final String message, final Throwable cause) {
        super(message, cause, false, true);
    }
}