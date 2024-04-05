package br.com.ntconsult.assembleia.application.v1.usecase.contracts;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;

public interface iniciarOuFinalizarPautaPorIdUseCase {
    PautaResponseDto iniciarOuFinalizarPauta(Long id, PautaStatus status);

}
