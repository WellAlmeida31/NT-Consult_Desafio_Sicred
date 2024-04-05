package br.com.ntconsult.assembleia.application.v1.usecase.contracts;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.domain.associado.AssociadoStatus;

public interface InativarOuAtivarAssociadoPorCpfUseCase {
    AssociadoResponseDto anativarOuAtivarAssociado(String cpf, AssociadoStatus status);
}
