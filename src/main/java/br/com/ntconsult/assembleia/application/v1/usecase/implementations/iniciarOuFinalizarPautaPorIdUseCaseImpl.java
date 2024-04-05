package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.contracts.iniciarOuFinalizarPautaPorIdUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class iniciarOuFinalizarPautaPorIdUseCaseImpl implements iniciarOuFinalizarPautaPorIdUseCase {

    private final PautaRepository repository;

    @Transactional
    @Override
    public PautaResponseDto iniciarOuFinalizarPauta(Long id, PautaStatus status) {
        return new PautaResponseDto(
                repository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new)
                        .iniciarOuEncerrarPauta(status));
    }
}
