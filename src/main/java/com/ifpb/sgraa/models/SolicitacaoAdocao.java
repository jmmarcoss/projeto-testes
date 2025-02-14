package com.ifpb.sgraa.models;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataSolicitacao;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status;

    @ManyToOne
    @JoinColumn(name = "adotante_id")
    private Adotante adotante;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public Adocao aprovar(Administrador admin) {
        this.status = StatusSolicitacao.APROVADA;
        Adocao adocao = new Adocao();
        adocao.setData(new Date());
        adocao.setTermoAssinado(true);
        adocao.setAdotante(adotante);
        adocao.setAnimal(animal);
        animal.atualizarStatus(StatusAdocao.ADOTADO);
        return adocao;
    }

    public void recusar(Administrador admin) {
        this.status = StatusSolicitacao.RECUSADA;
    }

}
