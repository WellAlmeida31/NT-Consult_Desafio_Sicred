package br.com.ntconsult.assembleia.domain.pauta;

import br.com.ntconsult.assembleia.domain.assembleia.Assembleia;
import br.com.ntconsult.assembleia.domain.base.EntityBase;
import br.com.ntconsult.assembleia.domain.voto.Voto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "pauta")
public class Pauta extends EntityBase {

    @Column(nullable = false)
    private String descricao;
    @Enumerated(EnumType.STRING)
    private PautaStatus status;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pauta_voto",
            joinColumns = @JoinColumn(name = "pauta_id"),
            inverseJoinColumns = @JoinColumn(name = "voto_id"))
    private List<Voto> votos = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="assembleia_id", nullable=false)
    private Assembleia assembleia;

    public Pauta(String descricao, LocalDateTime fim, Assembleia assembleia) {
        this.descricao = descricao;
        this.fim = fim;
        this.assembleia = assembleia;
        this.status = PautaStatus.INICIADA;
        this.inicio = LocalDateTime.now();
    }

    public Pauta encerrarPauta(){
        this.setStatus(PautaStatus.FINALIZADA);
        return this;
    }

    public Pauta iniciarOuEncerrarPauta(PautaStatus status){
        this.setStatus(status);
        return this;
    }

    public boolean estaIniciada(){
        return this.status.equals(PautaStatus.INICIADA);
    }

    public void adicionarVoto(Voto voto){
        this.votos.add(voto);
    }
}
