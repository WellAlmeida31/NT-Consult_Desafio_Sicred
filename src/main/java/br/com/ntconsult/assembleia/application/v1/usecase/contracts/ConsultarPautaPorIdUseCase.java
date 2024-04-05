package br.com.ntconsult.assembleia.application.v1.usecase.contracts;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;

public interface ConsultarPautaPorIdUseCase {
    PautaResponseDto consultarPauta(Long id);
}
