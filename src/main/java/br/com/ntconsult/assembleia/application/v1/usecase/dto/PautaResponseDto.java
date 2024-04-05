package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;

import java.time.LocalDateTime;

public record PautaResponseDto(Long id, String descricao, PautaStatus status, LocalDateTime inicio, LocalDateTime fim) {
    public PautaResponseDto(Pauta pauta) {
        this(pauta.getId(), pauta.getDescricao(), pauta.getStatus(), pauta.getInicio(), pauta.getFim());
    }
}
