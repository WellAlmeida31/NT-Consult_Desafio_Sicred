package br.com.ntconsult.assembleia.domain.pauta;


public enum PautaStatus {
    INICIADA("Iniciada"),
    FINALIZADA("Finalizada");

    private final String situacao;

    PautaStatus(String situacao) {
        this.situacao = situacao;
    }

}
