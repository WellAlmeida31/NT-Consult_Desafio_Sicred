package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.ConsultaTodasAssembleiasUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultaTodasAssembleiasUseCaseImpl implements ConsultaTodasAssembleiasUseCase {

    private final AssembleiaRepository repository;
    @Override
    public List<AssembleiaResponseDto> consutaTodasAsAssembleias() {
        return repository.findAll().stream().map(AssembleiaResponseDto::new).toList();
    }
}
