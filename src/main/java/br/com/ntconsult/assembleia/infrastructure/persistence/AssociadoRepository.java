package br.com.ntconsult.assembleia.infrastructure.persistence;

import br.com.ntconsult.assembleia.domain.associado.Associado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssociadoRepository extends AbstractRepository<Associado> {
    Optional<Associado> findByCpf(String cpf);
}
