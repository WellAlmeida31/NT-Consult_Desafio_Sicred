package br.com.ntconsult.assembleia.infrastructure.persistence;


import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import br.com.ntconsult.assembleia.domain.voto.Voto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends AbstractRepository<Voto> {
    boolean existsByAssociadosAndPautas(List<Associado> associados, List<Pauta> pautas);
}
