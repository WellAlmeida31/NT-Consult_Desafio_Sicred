package br.com.ntconsult.assembleia.domain.associado;


import br.com.ntconsult.assembleia.domain.base.EntityBase;
import br.com.ntconsult.assembleia.domain.voto.Voto;
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
@Table(name = "associado")
public class Associado extends EntityBase {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Enumerated(EnumType.STRING)
    private AssociadoStatus status;
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "associado_voto",
            joinColumns = @JoinColumn(name = "associado_id"),
            inverseJoinColumns = @JoinColumn(name = "voto_id"))
    private List<Voto> votos = new ArrayList<>();

    public Associado(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.status = AssociadoStatus.ABLE_TO_VOTE;
    }

    public Associado inativarOuAtivar(AssociadoStatus status){
        this.setStatus(status);
        return this;
    }

    public boolean podeVotar(){
        return this.status.equals(AssociadoStatus.ABLE_TO_VOTE);
    }

    public void adicionarVoto(Voto voto){
        this.votos.add(voto);
    }
}
