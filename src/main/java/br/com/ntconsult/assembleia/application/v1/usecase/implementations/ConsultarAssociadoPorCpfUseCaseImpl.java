package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.contracts.ConsultarAssociadoPorCpfUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultarAssociadoPorCpfUseCaseImpl implements ConsultarAssociadoPorCpfUseCase {

    private final AssociadoRepository repository;
    @Override
    public AssociadoResponseDto consultarAssociadoPorCpf(String cpf) {
        return new AssociadoResponseDto(repository.findByCpf(cpf).orElseThrow(EntityNotFoundException::new));
    }
}
