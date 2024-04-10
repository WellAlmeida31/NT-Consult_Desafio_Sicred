package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.domain.associado.AssociadoStatus;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static br.com.ntconsult.assembleia.domain.associado.AssociadoStatus.ABLE_TO_VOTE;
import static org.mockito.Mockito.*;

@DisplayName("InativarOuAtivarAssociadoPorCpfUseCaseImpl Test")
class InativarOuAtivarAssociadoPorCpfUseCaseImplTest {

    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private InativarOuAtivarAssociadoPorCpfUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInativarOuAtivarAssociado_Encontrado() {

        String cpf = "12345678900";
        Associado associado = new Associado();
        associado.setCpf(cpf);
        associado.setNome("Teste");
        associado.setStatus(ABLE_TO_VOTE);

        when(repository.findByCpf(cpf)).thenReturn(Optional.of(associado));

        AssociadoResponseDto responseDto = useCase.anativarOuAtivarAssociado(cpf, AssociadoStatus.UNABLE_TO_VOTE);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(AssociadoStatus.UNABLE_TO_VOTE, responseDto.status());

        verify(repository, times(1)).findByCpf(cpf);
    }

    @Test
    public void testInativarOuAtivarAssociado_NaoEncontrado() {
        String cpf = "12345678900";
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.anativarOuAtivarAssociado(cpf, AssociadoStatus.UNABLE_TO_VOTE);
        });

        verify(repository, times(1)).findByCpf(cpf);
        verify(repository, never()).save(any());
    }

}