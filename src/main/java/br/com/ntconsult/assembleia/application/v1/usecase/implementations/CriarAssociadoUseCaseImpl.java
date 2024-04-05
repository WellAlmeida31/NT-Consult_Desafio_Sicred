package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.CriarAssociadoUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriarAssociadoUseCaseImpl implements CriarAssociadoUseCase {

    private final AssociadoRepository repository;

    @Transactional
    @Override
    public AssociadoResponseDto criarAssociado(AssociadoRequestDto dto) {
        return new AssociadoResponseDto(repository.save(new Associado(dto.nome(), dto.cpf())));
    }
}
