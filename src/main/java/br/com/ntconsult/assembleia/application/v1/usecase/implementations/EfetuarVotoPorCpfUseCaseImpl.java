package br.com.ntconsult.assembleia.application.v1.usecase.implementations;


import br.com.ntconsult.assembleia.application.v1.kafka.Producer;
import br.com.ntconsult.assembleia.application.v1.usecase.contracts.EfetuarVotoPorCpfUseCase;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoKafka;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoRequestDto;
import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.voto.Voto;
import br.com.ntconsult.assembleia.domain.voto.VotoValor;
import br.com.ntconsult.assembleia.infrastructure.persistence.AssociadoRepository;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import br.com.ntconsult.assembleia.infrastructure.persistence.VotoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class EfetuarVotoPorCpfUseCaseImpl implements EfetuarVotoPorCpfUseCase {

    private final Producer producer;
    private final AssociadoRepository associadoRepository;
    private final PautaRepository pautaRepository;
    private final List<ValidarVoto> validarVoto;

    @Override
    public void efetuarVoto(VotoRequestDto dto) {
        producer.send(dto);
    }

    @Transactional
    @Override
    public void persistirVoto(VotoKafka votoKafka) {
        var associado = associadoRepository.findByCpf(votoKafka.getCpf()).orElseThrow(EntityNotFoundException::new);
        var pauta = pautaRepository.findById(votoKafka.getPauta()).orElseThrow(EntityNotFoundException::new);
        if(validarVoto.stream().anyMatch(valid -> !valid.validacao(associado, pauta))){
            log.info("Associado inativo, Pauta Finalizada ou voto já realizado");
            return;
        }
        var voto = new Voto(VotoValor.valueOf(votoKafka.getVotoValor()), List.of(associado), List.of(pauta));
        associado.adicionarVoto(voto);
        pauta.adicionarVoto(voto);
    }


    interface ValidarVoto {
        boolean validacao(Associado associado, Pauta pauta);
    }
    @Component
    static class ValidarAssociadoHabilEPautaIniciada implements ValidarVoto {
        @Override
        public boolean validacao(Associado associado, Pauta pauta) {
            return associado.podeVotar() && pauta.estaIniciada();
        }
    }
    @Component
    @RequiredArgsConstructor
    static class ValidarSeAssociadoJaVotou implements ValidarVoto {
        private final VotoRepository votoRepository;
        @Override
        public boolean validacao(Associado associado, Pauta pauta) {
            return !votoRepository.existsByAssociadosAndPautas(List.of(associado), List.of(pauta));
        }
    }


}
