package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.domain.assembleia.AssembleiaStatus;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("CriarPautaUseCaseImpl Test")
class CriarPautaUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private AssembleiaRepository assembleiaRepository;

    @InjectMocks
    private CriarPautaUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar uma pauta corretamente")
    public void testCriarPauta() {
        PautaRequestDto requestDto = new PautaRequestDto("Descrição da Pauta", LocalDateTime.now().plusMinutes(5), 1L);
        Assembleia assembleia = new Assembleia();
        assembleia.setId(1L);
        assembleia.setNome("Assembleia Teste");
        assembleia.setStatus(AssembleiaStatus.ABERTA);

        Pauta pautaSalva = new Pauta();
        pautaSalva.setId(1L);
        pautaSalva.setDescricao("Descrição da Pauta");
        pautaSalva.setFim(LocalDateTime.now().plusMinutes(5));
        pautaSalva.setAssembleia(assembleia);

        when(assembleiaRepository.findById(anyLong())).thenReturn(Optional.of(assembleia));
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaSalva);

        PautaResponseDto responseDto = useCase.criarPauta(requestDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.id());
        Assertions.assertEquals("Descrição da Pauta", responseDto.descricao());
        verify(assembleiaRepository, times(1)).findById(anyLong());
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

}