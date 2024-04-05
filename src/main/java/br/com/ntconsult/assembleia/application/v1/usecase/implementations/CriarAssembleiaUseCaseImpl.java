package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.CriarAssembleiaUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarAssembleiaUseCaseImpl implements CriarAssembleiaUseCase {

    private final AssembleiaRepository repository;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public AssembleiaResponseDto criarAssembleia(AssembleiaRequestDto dto) {
        return new AssembleiaResponseDto(repository.save(new Assembleia(dto.nome())));
    }
}
