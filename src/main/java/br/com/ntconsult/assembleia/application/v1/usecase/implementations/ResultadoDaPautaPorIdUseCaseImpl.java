package br.com.ntconsult.assembleia.application.v1.usecase.implementations;

import br.com.ntconsult.assembleia.application.v1.usecase.contracts.ResultadoDaPautaPorIdUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.ResultadoPautaResponse;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.voto.VotoValor;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import br.com.ntconsult.assembleia.infrastructure.web.handler.exception.PautaSemVotosException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResultadoDaPautaPorIdUseCaseImpl implements ResultadoDaPautaPorIdUseCase {

    private final PautaRepository pautaRepository;

    @Override
    public ResultadoPautaResponse resultadoDaPautaPorId(Long id) {
        var pauta = pautaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        validarExistenciaDeVotos(pauta);
        var sim = votosContagem(pauta, VotoValor.SIM);
        var nao = votosContagem(pauta, VotoValor.NAO);
        var descResult = descricaoResultado(sim, nao);
        return new ResultadoPautaResponse(pauta.getId(), pauta.getDescricao(), sim, nao, descResult, pauta.getAssembleia().getNome());
    }

    protected Long votosContagem(Pauta pauta, VotoValor valor){
        return pauta.getVotos().stream().filter(voto -> voto.getVotoValor().equals(valor)).count();
    }

    protected String descricaoResultado(Long sim, Long nao){
        if(sim > nao) return "Pauta deferida por maioria de votos SIM";
        if(nao > sim) return "Pauta indeferida por maioria de votos NAO";
        return "Pauta sem resultados devido empate";
    }

    protected void validarExistenciaDeVotos(Pauta pauta){
        if(pauta.getVotos().isEmpty()) throw new PautaSemVotosException("Não há votos para contagem nesta Pauta");
    }


}
