package br.com.ntconsult.assembleia.domain.associado;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AssociadoStatus {
    ABLE_TO_VOTE("Usuário ativo"),
    UNABLE_TO_VOTE("Usuário inativo");
    private final String situacao;

    AssociadoStatus(String situacao) {
        this.situacao = situacao;
    }

}
