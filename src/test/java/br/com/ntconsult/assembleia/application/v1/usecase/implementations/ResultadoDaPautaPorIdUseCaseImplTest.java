package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.ResultadoPautaResponse;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.voto.Voto;
import br.com.ntconsult.assembleia.domain.voto.VotoValor;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.PautaSemVotosException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@DisplayName("ResultadoDaPautaPorIdUseCaseImpl Test")
class ResultadoDaPautaPorIdUseCaseImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private ResultadoDaPautaPorIdUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testResultadoDaPautaPorId_PautaComVotos() {
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        pauta.setDescricao("Pauta de Teste");
        pauta.setAssembleia(new Assembleia("Assembleia de Teste"));

        Voto voto1 = new Voto();
        voto1.setVotoValor(VotoValor.SIM);
        Voto voto2 = new Voto();
        voto2.setVotoValor(VotoValor.SIM);
        Voto voto3 = new Voto();
        voto3.setVotoValor(VotoValor.NAO);
        List<Voto> votos = List.of(voto1, voto2, voto3);

        pauta.setVotos(votos);

        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));

        ResultadoPautaResponse response = useCase.resultadoDaPautaPorId(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(id, response.pautaId());
        Assertions.assertEquals("Pauta de Teste", response.pautaDescricao());
        Assertions.assertEquals(2L, response.votosSim());
        Assertions.assertEquals(1L, response.votosNao());
        Assertions.assertEquals("Pauta deferida por maioria de votos SIM", response.resultado());
        Assertions.assertEquals("Assembleia de Teste", response.assembleiaNome());
    }

    @Test
    public void testResultadoDaPautaPorId_PautaSemVotos() {
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        pauta.setDescricao("Pauta de Teste");
        pauta.setAssembleia(new Assembleia("Assembleia de Teste"));
        pauta.setVotos(new ArrayList<>());

        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));

        Assertions.assertThrows(PautaSemVotosException.class, () -> {
            useCase.resultadoDaPautaPorId(id);
        });
    }

}