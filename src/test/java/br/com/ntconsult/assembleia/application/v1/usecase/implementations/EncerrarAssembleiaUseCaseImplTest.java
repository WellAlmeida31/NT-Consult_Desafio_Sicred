package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.domain.assembleia.AssembleiaStatus;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.pauta.PautaStatus;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("EncerrarAssembleiaUseCaseImpl Test")
class EncerrarAssembleiaUseCaseImplTest {
    @Mock
    private AssembleiaRepository repository;
    @Mock
    private Pauta pauta;

    @InjectMocks
    private EncerrarAssembleiaUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEncerrarAssembleiaPorId_AssembleiaEncontrada() {
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1L);
        assembleia.setNome("Assembleia Teste");
        assembleia.setStatus(AssembleiaStatus.ABERTA);
        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        pauta1.setDescricao("Pauta 1");
        pauta1.setStatus(PautaStatus.INICIADA);
        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);
        pauta2.setDescricao("Pauta 2");
        pauta2.setStatus(PautaStatus.INICIADA);
        assembleia.getPautas().add(pauta1);
        assembleia.getPautas().add(pauta2);

        when(repository.findById(anyLong())).thenReturn(Optional.of(assembleia));

        useCase.encerrarAssembleiaPorId(1L);

        verify(repository, times(1)).findById(1L);

    }

    @Test
    public void testEncerrarAssembleiaPorId_AssembleiaNaoEncontrada() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.encerrarAssembleiaPorId(1L);
        });

        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any());
    }
}