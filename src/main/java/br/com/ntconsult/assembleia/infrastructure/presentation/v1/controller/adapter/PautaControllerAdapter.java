package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.adapter;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.ResultadoPautaResponse;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.ConsultarPautaPorIdUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.CriarPautaUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.ResultadoDaPautaPorIdUseCaseImpl;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.iniciarOuFinalizarPautaPorIdUseCaseImpl;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;
import br.com.ntconsult.assembleia.infrastructure.presentation.path.Paths;
import br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port.PautaControllerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class PautaControllerAdapter implements PautaControllerPort {

    private final CriarPautaUseCaseImpl criarPautaUseCase;
    private final ConsultarPautaPorIdUseCaseImpl consultarPautaPorIdUseCase;
    private final iniciarOuFinalizarPautaPorIdUseCaseImpl iniciarOuFinalizarPautaPorIdUseCase;
    private final ResultadoDaPautaPorIdUseCaseImpl resultadoDaPautaPorIdUseCase;
    @Override
    public ResponseEntity<PautaResponseDto> criarPauta(PautaRequestDto dto, UriComponentsBuilder uriBuilder) {
        var pautaDto = criarPautaUseCase.criarPauta(dto);
        return ResponseEntity
                .created(uriBuilder
                        .path(Paths.V1.Pauta.PAUTA + Paths.Pauta.FIND)
                        .buildAndExpand(pautaDto.id())
                        .toUri())
                .body(pautaDto);
    }

    @Override
    public ResponseEntity<PautaResponseDto> buscarPautaPorId(Long id) {
        return ResponseEntity.ok(consultarPautaPorIdUseCase.consultarPauta(id));
    }

    @Override
    public ResponseEntity<PautaResponseDto> iniciarOuFinalizarPauta(Long id, PautaStatus status) {
        return ResponseEntity.accepted().body(iniciarOuFinalizarPautaPorIdUseCase.iniciarOuFinalizarPauta(id, status));
    }

    @Override
    public ResponseEntity<ResultadoPautaResponse> resultadoDaPauta(Long id) {
        return ResponseEntity.ok(resultadoDaPautaPorIdUseCase.resultadoDaPautaPorId(id));
    }
}
