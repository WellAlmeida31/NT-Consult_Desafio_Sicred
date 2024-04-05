package br.com.ntconsult.assembleia.application.v1.kafka;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoKafka;
import br.com.ntconsult.assembleia.application.v1.usecase.implementations.EfetuarVotoPorCpfUseCaseImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Consumer {
    private final EfetuarVotoPorCpfUseCaseImpl efetuarVotoPorCpfUseCase;

    @KafkaListener(topics = "${app.kafka.producer.topics}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        efetuarVotoPorCpfUseCase
                .persistirVoto(VotoKafka
                        .builder()
                        .votoJson(payload.value())
                        .build());

    }
}
