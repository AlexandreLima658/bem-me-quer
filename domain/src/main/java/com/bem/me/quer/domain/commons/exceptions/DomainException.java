package com.bem.me.quer.domain.commons.exceptions;

public class DomainException extends NoStackTraceException{

    private final ErrorInfo errorInfo;

    public DomainException(final ErrorInfo errorInfo) {
        super(errorInfo.message());
        this.errorInfo = errorInfo;
    }

    public DomainException with(final ErrorInfo errorInfo) {
        return new DomainException(errorInfo);
    }

    public DomainException with(final String message) {
        return new DomainException(new ErrorInfo(message));
    }

    public ErrorInfo errorInfo() {
        return errorInfo;
    }
}
