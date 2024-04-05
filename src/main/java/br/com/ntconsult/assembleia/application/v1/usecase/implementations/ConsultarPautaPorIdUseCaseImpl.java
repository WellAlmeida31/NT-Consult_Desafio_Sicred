package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.ConsultarPautaPorIdUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarPautaPorIdUseCaseImpl implements ConsultarPautaPorIdUseCase {

    private final PautaRepository repository;
    @Override
    public PautaResponseDto consultarPauta(Long id) {
        return new PautaResponseDto(repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new));
    }
}
