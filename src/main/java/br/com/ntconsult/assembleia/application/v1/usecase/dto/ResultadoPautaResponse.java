package br.com.ntconsult.assembleia.application.v1.usecase.dto;

public record ResultadoPautaResponse(Long pautaId,
                                     String pautaDescricao,
                                     Long votosSim,
                                     Long votosNao,
                                     String resultado,
                                     String assembleiaNome) {

}
