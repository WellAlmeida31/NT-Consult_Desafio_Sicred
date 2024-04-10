package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
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

@DisplayName("ConsultarPautaPorIdUseCaseImpl Test")
class ConsultarPautaPorIdUseCaseImplTest {
    @Mock
    private PautaRepository repository;

    @InjectMocks
    private ConsultarPautaPorIdUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar uma Pauta com sucesso pelo Id")
    public void testConsultarPauta() {
        Long id = 1L;
        Pauta pautaMock = new Pauta();
        pautaMock.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(pautaMock));

        PautaResponseDto responseDto = useCase.consultarPauta(id);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(id, responseDto.id());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve devolver EntityNotFoundException para uma pauta inmexistente pelo id")
    public void testConsultarPautaNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.consultarPauta(id);
        });
        verify(repository, times(1)).findById(id);
    }
}
