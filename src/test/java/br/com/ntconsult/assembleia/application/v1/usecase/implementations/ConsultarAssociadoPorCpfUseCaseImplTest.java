package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayName("ConsultarAssociadoPorCpfUseCaseImpl Test")
class ConsultarAssociadoPorCpfUseCaseImplTest {
    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private ConsultarAssociadoPorCpfUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar um Associado com sucesso pelo cpf")
    public void testConsultarAssociadoPorCpf() {
        String cpf = "12345678900";
        Associado associadoMock = new Associado();
        associadoMock.setCpf(cpf);

        when(repository.findByCpf(cpf)).thenReturn(Optional.of(associadoMock));

        AssociadoResponseDto responseDto = useCase.consultarAssociadoPorCpf(cpf);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(cpf, responseDto.cpf());
        verify(repository, times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Deve receber EntityNotFoundException ao consultar um Associado inexistente pelo cpf")
    public void testConsultarAssociadoPorCpfNotFound() {
        String cpf = "12345678900";
        when(repository.findByCpf(cpf)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.consultarAssociadoPorCpf(cpf);
        });
        verify(repository, times(1)).findByCpf(cpf);
    }

}
