package br.com.ntconsult.assembleia.infrastructure.presentation.path;

public interface Paths {

    String API = "/api";
    String VERSION_APP_V1 = "/v1";
    String VERSION_APP_V2 = "/v2";
    String CLIENT = API + VERSION_APP_V1 + "/client";

    interface V1 {
        interface Assembleia {
            String ASSEMBLEIA = CLIENT + "/assembleia";
        }
        interface Associado {
            String ASSOCIADO = CLIENT + "/associado";
        }
        interface Pauta {
            String PAUTA = CLIENT + "/pauta";
        }
        interface Voto {
            String VOTO = CLIENT + "/voto";
        }
    }

    interface Assembleia {
        String FIND_ALL = "/findall";
        String ID = "/{id}";
        String FIND = "/find" + ID;
        String CREATE = "/create";
        String ENCLOSE = "/enclose" + ID;

    }

    interface Associado {
        String CREATE = "/create";
        String CPF = "/{cpf}";
        String FIND = "/find" + CPF;
        String STATUS = "/status" + CPF;
    }

    interface Pauta {
        String CREATE = "/create";
        String ID = "/{id}";
        String FIND = "/find" + ID;
        String STATUS = "/status" + ID;
        String RESULT = "/result" + ID;
        String REPORT = "/report" + ID;
    }

    interface Voto {
        String CREATE = "/create";

    }

    interface Placeholder {
        String ID = "id";
        String CPF = "cpf";
        String STATUS = "status";
    }

    interface V2 {

    }




}
