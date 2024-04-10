package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.AssociadoResponseDto;
import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("CriarAssociadoUseCaseImpl Test")
class CriarAssociadoUseCaseImplTest {

    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private CriarAssociadoUseCaseImpl useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar um Associado com sucesso")
    public void testCriarAssociado() {
        AssociadoRequestDto requestDto = new AssociadoRequestDto("Nome do Associado", "12345678900");
        Associado associadoSalvo = new Associado();
        associadoSalvo.setId(1L);
        associadoSalvo.setNome("Nome do Associado");
        associadoSalvo.setCpf("12345678900");

        when(repository.save(any(Associado.class))).thenReturn(associadoSalvo);

        AssociadoResponseDto responseDto = useCase.criarAssociado(requestDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(1L, responseDto.id());
        Assertions.assertEquals("Nome do Associado", responseDto.nome());
        Assertions.assertEquals("12345678900", responseDto.cpf());
        verify(repository, times(1)).save(any(Associado.class));
    }
}