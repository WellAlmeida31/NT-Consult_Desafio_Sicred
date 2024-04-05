package br.com.ntconsult.assembleia.infrastructure.persistence;

import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import org.springframework.stereotype.Repository;

@Repository
public interface AssembleiaRepository extends AbstractRepository<Assembleia> {
}
