package br.com.ntconsult.assembleia.domain.voto;

import br.com.ntconsult.assembleia.domain.associado.Associado;
import br.com.ntconsult.assembleia.domain.base.EntityBase;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "voto")
public class Voto extends EntityBase {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VotoValor votoValor;
    @ManyToMany(mappedBy = "votos")
    private List<Associado> associados = new ArrayList<>();
    @ManyToMany(mappedBy = "votos")
    private List<Pauta> pautas = new ArrayList<>();
}
