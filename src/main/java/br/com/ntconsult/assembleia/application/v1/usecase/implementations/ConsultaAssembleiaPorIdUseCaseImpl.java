package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.ConsultaAssembleiaPorIdUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsultaAssembleiaPorIdUseCaseImpl implements ConsultaAssembleiaPorIdUseCase {

    private final AssembleiaRepository repository;
    @Override
    public AssembleiaResponseDto consultaAssembleiaPorId(Long id) {
        var assembleia = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new AssembleiaResponseDto(assembleia, assembleia.getPautas().stream().map(PautaResponseDto::new).toList());
    }
}
