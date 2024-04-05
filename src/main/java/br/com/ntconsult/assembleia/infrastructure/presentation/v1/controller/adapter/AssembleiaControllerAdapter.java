package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.adapter;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.ConsultaAssembleiaPorIdUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.ConsultaTodasAssembleiasUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.CriarAssembleiaUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.EncerrarAssembleiaUseCaseImpl;
import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port.AssembleiaControllerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssembleiaControllerAdapter implements AssembleiaControllerPort {

    private final ConsultaTodasAssembleiasUseCaseImpl consultaTodasAssembleiasUseCase;
    private final CriarAssembleiaUseCaseImpl criarAssembleiaUseCase;
    private final ConsultaAssembleiaPorIdUseCaseImpl consultaAssembleiaPorIdUseCase;
    private final EncerrarAssembleiaUseCaseImpl encerrarAssembleiaUseCase;
    @Override
    public ResponseEntity<List<AssembleiaResponseDto>> findall() {
        return ResponseEntity.ok(consultaTodasAssembleiasUseCase.consutaTodasAsAssembleias());
    }

    @Override
    public ResponseEntity<AssembleiaResponseDto> criarAssembleia(AssembleiaRequestDto dto, UriComponentsBuilder uriBuilder) {
        var assembleiaDto = criarAssembleiaUseCase.criarAssembleia(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(Paths.V1.Assembleia.ASSEMBLEIA + Paths.Assembleia.FIND)
                        .buildAndExpand(assembleiaDto.id())
                        .toUri())
                .body(assembleiaDto);
    }

    @Override
    public ResponseEntity encerrarAssembleia(Long id) {
        encerrarAssembleiaUseCase.encerrarAssembleiaPorId(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AssembleiaResponseDto> buscarAssembleia(Long id) {
        return ResponseEntity.ok(consultaAssembleiaPorIdUseCase.consultaAssembleiaPorId(id));
    }
}
