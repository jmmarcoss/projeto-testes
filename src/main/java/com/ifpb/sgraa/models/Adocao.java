package com.ifpb.sgraa.models;

import com.ifpb.sgraa.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adocao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date data;

  private boolean termoAssinado;

  @OneToOne
  @JoinColumn(name = "animal_id")
  private Animal animal;

  @ManyToOne
  @JoinColumn(name = "adotante_id")
  private Adotante adotante;

  @ManyToOne
  @JoinColumn(name = "abrigo_id")
  private Abrigo abrigo;

  public void finalizarAdocao() {
    this.animal.atualizarStatus(StatusAdocao.ADOTADO);
    abrigo.getProcessosAdocao().add(this);
  }

  public void cancelarAdocao() {
    this.animal.atualizarStatus(StatusAdocao.DISPONIVEL);
    abrigo.getProcessosAdocao().remove(this);
  }
}
