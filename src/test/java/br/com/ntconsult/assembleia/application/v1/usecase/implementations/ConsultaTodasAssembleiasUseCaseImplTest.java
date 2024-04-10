package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("ConsultaTodasAssembleiasUseCaseImpl Test")
class ConsultaTodasAssembleiasUseCaseImplTest {
    @Mock
    private AssembleiaRepository repository;

    @InjectMocks
    private ConsultaTodasAssembleiasUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar todas as Assembleias com sucesso")
    public void testConsultaTodasAsAssembleias() {
        Assembleia assembleia1 = new Assembleia();
        assembleia1.setId(1L);
        Assembleia assembleia2 = new Assembleia();
        assembleia2.setId(2L);
        List<Assembleia> assembleias = Arrays.asList(assembleia1, assembleia2);

        when(repository.findAll()).thenReturn(assembleias);

        List<AssembleiaResponseDto> responseDtoList = useCase.consutaTodasAsAssembleias();

        Assertions.assertNotNull(responseDtoList);
        Assertions.assertEquals(2, responseDtoList.size());
        Assertions.assertEquals(1L, responseDtoList.get(0).id());
        Assertions.assertEquals(2L, responseDtoList.get(1).id());
        verify(repository, times(1)).findAll();
    }

}