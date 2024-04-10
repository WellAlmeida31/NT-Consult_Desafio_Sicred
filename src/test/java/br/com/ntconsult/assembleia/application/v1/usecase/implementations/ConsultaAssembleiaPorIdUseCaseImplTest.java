package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@DisplayName("ConsultaAssembleiaPorIdUseCaseImpl Test")
class ConsultaAssembleiaPorIdUseCaseImplTest {
    @Mock
    private AssembleiaRepository repository;

    @InjectMocks
    private ConsultaAssembleiaPorIdUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve consultar uma Assembelia com sucesso pelo id")
    public void testConsultaAssembleiaPorId() {
        Long id = 1L;
        Assembleia assembleiaMock = new Assembleia();
        assembleiaMock.setId(id);
        assembleiaMock.setPautas(List.of(new Pauta()));

        when(repository.findById(id)).thenReturn(Optional.of(assembleiaMock));

        AssembleiaResponseDto responseDto = useCase.consultaAssembleiaPorId(id);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(id, responseDto.id());
        Assertions.assertEquals(1, responseDto.pautaResponseDtoList().size());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve emitir EntityNotFoundException para uma assembleia inexistente")
    public void testConsultaAssembleiaPorIdNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            useCase.consultaAssembleiaPorId(id);
        });
        verify(repository, times(1)).findById(id);
    }
}
