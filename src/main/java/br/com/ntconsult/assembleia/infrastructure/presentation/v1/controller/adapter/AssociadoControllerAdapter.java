package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.adapter;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.ConsultarAssociadoPorCpfUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.CriarAssociadoUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.InativarOuAtivarAssociadoPorCpfUseCaseImpl;
import br.com.ntconsult.assembleia.domain.associado.AssociadoStatus;
import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port.AssociadoControllerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class AssociadoControllerAdapter implements AssociadoControllerPort {

    private final CriarAssociadoUseCaseImpl criarAssociadoUseCase;
    private final ConsultarAssociadoPorCpfUseCaseImpl consultarAssociadoPorCpfUseCase;
    private final InativarOuAtivarAssociadoPorCpfUseCaseImpl inativarAssociadoPorCpfUseCase;
    @Override
    public ResponseEntity<AssociadoResponseDto> criarAssociado(AssociadoRequestDto dto, UriComponentsBuilder uriBuilder) {
        var associadoDto = criarAssociadoUseCase.criarAssociado(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(Paths.V1.Associado.ASSOCIADO + Paths.Associado.FIND)
                        .buildAndExpand(associadoDto.id())
                        .toUri())
                .body(associadoDto);
    }

    @Override
    public ResponseEntity<AssociadoResponseDto> buscarAssociadoPorCpf(String cpf) {
        return ResponseEntity.ok(consultarAssociadoPorCpfUseCase.consultarAssociadoPorCpf(cpf));
    }

    @Override
    public ResponseEntity<AssociadoResponseDto> inativarOuAtivarAssociado(String cpf, AssociadoStatus status) {
        return ResponseEntity
                .accepted()
                .body(inativarAssociadoPorCpfUseCase.anativarOuAtivarAssociado(cpf, status));
    }


}
