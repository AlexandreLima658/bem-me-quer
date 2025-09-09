package com.bem.me.quer.infra.rest;

import com.bem.me.quer.domain.commons.exceptions.ErrorInfo;
import com.bem.me.quer.domain.commons.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String MESSAGE_ERROR_500 = """
            Desculpe-nos, ocorreu um erro interno no servidor.
            Nossa equipe já foi notificada sobre o problema e estamos trabalhando para 
            resolvê-lo o mais rápido possível. Por favor, tente novamente mais tarde. 
            Obrigado pela sua compreensão.
            """.replace("\n", " ");

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorInfo> handlerNotFoundException(final NotFoundException ex) {
        log.error(ex.errorInfo().message());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.errorInfo());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorInfo> handlerException(final Exception ex) {
        log.error(ex.getMessage());
        final var error = new ErrorInfo(MESSAGE_ERROR_500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
