package br.com.ntconsult.assembleia.infrastructure.web.handler.exception;

import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class PautaSemVotosException extends RuntimeException implements Serializable {
    public PautaSemVotosException(String message) {
        super(message);
    }
}
