package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.contracts.EncerrarAssembleiaUseCase;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncerrarAssembleiaUseCaseImpl implements EncerrarAssembleiaUseCase {

    private final AssembleiaRepository repository;

    @Transactional
    @Override
    public void encerrarAssembleiaPorId(Long id) {
         repository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                 .encerrarAssembleia()
                 .getPautas()
                 .forEach(Pauta::encerrarPauta);
    }
}
