package br.com.ntconsult.assembleia.domain.assembleia;

import br.com.ntconsult.assembleia.domain.base.EntityBase;
import br.com.ntconsult.assembleia.domain.pauta.Pauta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "assembleia")
public class Assembleia extends EntityBase {

    @Column(name = "nome", nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    private AssembleiaStatus status;
    @OneToMany(mappedBy="assembleia", fetch = FetchType.EAGER)
    private List<Pauta> pautas = new ArrayList<>();

    public Assembleia(String nome) {
        this.nome = nome;
        this.status = AssembleiaStatus.ABERTA;
    }

    public Assembleia encerrarAssembleia(){
        this.setStatus(AssembleiaStatus.ENCERRADA);
        return this;
    }

    public boolean isOpen(){
        return this.status.equals(AssembleiaStatus.ABERTA);
    }

}
