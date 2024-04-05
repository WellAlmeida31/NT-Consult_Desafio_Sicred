package br.com.ntconsult.assembleia.infrastructure.web.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.FieldError;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorMessage(String message, String localizedItem, Throwable cause, StackTraceElement[] stackTrace) {
    public ErrorMessage(FieldError fieldError) {
        this(fieldError.getDefaultMessage(), fieldError.getField(),null,null);
    }
    public ErrorMessage(String message, StackTraceElement[] stackTrace, Throwable cause) {
        this(message,null,cause,stackTrace);
    }
}
