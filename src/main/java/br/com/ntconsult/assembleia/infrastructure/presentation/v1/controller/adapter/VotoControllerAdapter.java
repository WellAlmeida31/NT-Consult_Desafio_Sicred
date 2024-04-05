package br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.adapter;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.EfetuarVotoPorCpfUseCaseImpl;
import br.com.ntconsult.assembleia.infrastructure.presentation.v1.controller.port.VotoControllerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotoControllerAdapter implements VotoControllerPort {

    private final EfetuarVotoPorCpfUseCaseImpl efetuarVotoPorCpfUseCase;
    @Override
    public ResponseEntity<?> votar(VotoRequestDto dto) {
        efetuarVotoPorCpfUseCase.efetuarVoto(dto);
        return ResponseEntity.ok().build();
    }
}
