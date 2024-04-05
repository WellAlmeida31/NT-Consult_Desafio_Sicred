package br.com.ntconsult.assembleia.application.v1.task;

import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.infrastructure.persistence.PautaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PautaScheduler {

    private final PautaRepository pautaRepository;

    /**
     * Consulta as pautas abertas que devem ser encerradas
     * Task com lock para que outras instancias não executem a mesma task
     *
     * @since {@literal 30/03/2024}
     */
    @Scheduled(cron = Cron.A_CADA_1_MINUTO)
    @SchedulerLock(name = "PautaScheduler_pautaSchedulerTask")
    @Transactional(Transactional.TxType.REQUIRED)
    public void pautaSchedulerTask(){
        pautaRepository.findByFimBeforeNowAndStatus_Iniciada().forEach(Pauta::encerrarPauta);
    }
}
