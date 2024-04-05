package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import br.com.ntconsult.assembleia.application.v1.validation.Cpf;
import br.com.ntconsult.assembleia.domain.voto.VotoValor;
import jakarta.validation.constraints.NotNull;

public record VotoRequestDto(
        @NotNull VotoValor votoValor,
        @Cpf String cpf,
        @NotNull Long pautaId) {
}
