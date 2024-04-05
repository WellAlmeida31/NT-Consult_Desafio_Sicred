package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.domain.associado.AssociadoStatus;

public record AssociadoResponseDto(Long id, String nome, String cpf, AssociadoStatus status) {
    public AssociadoResponseDto(Associado associado){
        this(associado.getId(), associado.getNome(), associado.getCpf(), associado.getStatus());
    }
}
