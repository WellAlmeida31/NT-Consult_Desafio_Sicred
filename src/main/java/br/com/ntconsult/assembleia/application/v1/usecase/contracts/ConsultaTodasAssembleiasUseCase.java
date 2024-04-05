package br.com.ntconsult.assembleia.application.v1.usecase.contracts;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;

import java.util.List;

public interface ConsultaTodasAssembleiasUseCase {
    List<AssembleiaResponseDto> consutaTodasAsAssembleias();
}
