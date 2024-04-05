package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import br.com.ntconsult.assembleia.application.v1.validation.Cpf;
import jakarta.validation.constraints.NotEmpty;

public record AssociadoRequestDto(@NotEmpty String nome, @Cpf String cpf) {
}
