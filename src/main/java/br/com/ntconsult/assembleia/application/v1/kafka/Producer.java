package br.com.ntconsult.assembleia.application.v1.kafka;

import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoKafka;
import br.com.ntconsult.assembleia.application.v1.usecase.dto.VotoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {
    @Value("${app.kafka.producer.topics}")
    private String topico;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(VotoRequestDto votoDto) {
        try {
            this.kafkaTemplate.send(this.topico,
                    VotoKafka
                            .builder()
                            .voto(votoDto)
                            .build()
                            .toJson());

        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            throw new RuntimeException();
        }
    }
}
