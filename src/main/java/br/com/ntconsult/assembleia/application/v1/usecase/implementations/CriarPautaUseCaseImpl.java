package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.contracts.CriarPautaUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaRequestDto;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.PautaResponseDto;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssembleiaRepository;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.AssembleiaValidationException;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.DataValidationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CriarPautaUseCaseImpl implements CriarPautaUseCase {

    private final PautaRepository pautaRepository;
    private final AssembleiaRepository assembleiaRepository;

    /**
     * Consulta a Assembleia para Criação da Pauta, a Assembleia não deve estar ENCERRADA
     * Se não for informado a tempo fim da Pauta, será assumido 1 minuto de suração da Pauta
     * A duração da pauta não ser informado uma data anterior ao dia ou hora atual.
     * Toda Pauta é criada com status INICIADA e encerrada automaticamente via task
     *
     * @param dto PautaRequestDto
     * @return PautaResponseDto
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public PautaResponseDto criarPauta(PautaRequestDto dto) {
        var assembleia = assembleiaRepository.findById(dto.assembleiaId()).orElseThrow(EntityNotFoundException::new);
        if(!assembleia.isOpen()) throw new AssembleiaValidationException("Não é possível criar Pauta em Assembleia encerrada");
        return new PautaResponseDto(
                pautaRepository.save(
                        new Pauta(dto.descricao(), getDataFim(dto.fim()), assembleia)));
    }

    protected LocalDateTime getDataFim(LocalDateTime dataFim){
        if(Objects.isNull(dataFim)) return LocalDateTime.now().plusMinutes(1L);
        if (dataFim.isBefore(LocalDateTime.now())) throw new DataValidationException("A data de fim da Pauta é anterior a data de início");
        return dataFim;
    }
}
