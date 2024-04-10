package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayName("iniciarOuFinalizarPautaPorIdUseCaseImpl Test")
class iniciarOuFinalizarPautaPorIdUseCaseImplTest {

    @Mock
    private PautaRepository repository;

    @InjectMocks
    private iniciarOuFinalizarPautaPorIdUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIniciarOuFinalizarPauta_Encontrada() {
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        pauta.setDescricao("Pauta de Teste");
        pauta.setStatus(PautaStatus.INICIADA);

        when(repository.findById(id)).thenReturn(Optional.of(pauta));

        PautaResponseDto responseDto = useCase.iniciarOuFinalizarPauta(id, PautaStatus.FINALIZADA);
        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(PautaStatus.FINALIZADA, responseDto.status());

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testIniciarOuFinalizarPauta_NaoEncontrada() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.iniciarOuFinalizarPauta(id, PautaStatus.FINALIZADA);
        });

        verify(repository, times(1)).findById(id);
        verify(repository, never()).save(any());
    }

}