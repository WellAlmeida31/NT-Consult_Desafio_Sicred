package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record PautaRequestDto(@NotEmpty @NotBlank String descricao, LocalDateTime fim, @NotNull Long assembleiaId) {
}
