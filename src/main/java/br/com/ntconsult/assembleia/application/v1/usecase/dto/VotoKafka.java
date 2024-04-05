package br.com.ntconsult.assembleia.application.v1.usecase.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VotoKafka {
    private String votoValor;
    private String cpf;
    private Long pauta;

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static VotoProducerBuilder builder(){
        return new VotoProducerBuilder();
    }

    public static class VotoProducerBuilder {
        private VotoRequestDto votoDto;
        private String json;

        public VotoProducerBuilder voto(VotoRequestDto votoDto){
            this.votoDto = votoDto;
            return this;
        }

        public VotoProducerBuilder votoJson(String json){
            this.json = json;
            return this;
        }

        public VotoKafka build(){
            if(!Objects.isNull(this.votoDto)) return new VotoKafka(this.votoDto.votoValor().name(),
                    this.votoDto.cpf(),
                    this.votoDto.pautaId());
            else if (!this.json.isEmpty()) return new Gson().fromJson(this.json, VotoKafka.class);
            return new VotoKafka();
        }
    }
}
