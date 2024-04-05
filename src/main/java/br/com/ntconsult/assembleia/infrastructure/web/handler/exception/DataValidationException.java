package br.com.ntconsult.assembleia.infrastructure.web.handler.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class DataValidationException extends RuntimeException implements Serializable {
    public DataValidationException(String message) {
        super(message);
    }
    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
