package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssembleiaResponseDto;
import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CriarAssembleiaUseCaseImpl Test")
class CriarAssembleiaUseCaseImplTest {
    @Mock
    private AssembleiaRepository repository;

    @InjectMocks
    private CriarAssembleiaUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar uma assembleia com sucesso")
    public void testCriarAssembleia() {
        AssembleiaRequestDto requestDto = new AssembleiaRequestDto("Nome da Assembleia");
        Assembleia assembleiaSalva = new Assembleia();
        assembleiaSalva.setId(1L);
        assembleiaSalva.setNome("Nome da Assembleia");

        when(repository.save(any(Assembleia.class))).thenReturn(assembleiaSalva);

        AssembleiaResponseDto responseDto = useCase.criarAssembleia(requestDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.id());
        Assertions.assertEquals("Nome da Assembleia", responseDto.nome());
        verify(repository, times(1)).save(any(Assembleia.class));
    }

}