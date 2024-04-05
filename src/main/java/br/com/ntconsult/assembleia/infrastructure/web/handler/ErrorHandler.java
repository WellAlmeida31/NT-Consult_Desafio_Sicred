package br.com.ntconsult.assembleia.infrastructure.web.handler;

import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.AssembleiaValidationException;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.DataValidationException;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.PautaSemVotosException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessage>> tratarErro400(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getFieldErrors()
                        .stream()
                        .map(ErrorMessage::new)
                        .toList());
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ErrorMessage> tratarErroDataPautaValidacao(DataValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(),null,ex.getCause()));
    }


    @ExceptionHandler(AssembleiaValidationException.class)
    public ResponseEntity<ErrorMessage> tratarErroAssembleiaValidacao(AssembleiaValidationException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(),null,ex.getCause()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> tratarBadRequestException(NullPointerException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> tratarRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }


    @ExceptionHandler(PautaSemVotosException.class)
    public ResponseEntity<ErrorMessage> tratarPautaSemVotoException(PautaSemVotosException ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(ex.getMessage(), null, ex.getCause()));
    }
}
