package com.ifpb.sgraa.models;

import com.ifpb.sgraa.enums.EspecieAnimal;
import com.ifpb.sgraa.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Enumerated(EnumType.STRING)
  private EspecieAnimal especie;

  private String raca;
  private int idade;

  @Enumerated(EnumType.STRING)
  private StatusAdocao statusAdocao;

  @Temporal(TemporalType.DATE)
  private Date dataEntrada;

  @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TratamentoMedico> tratamentos = new ArrayList<>();

  @OneToOne(mappedBy = "animal")
  private Adocao adocao;

  @ManyToOne
  @JoinColumn(name = "resgate_id")
  private Resgate resgate;

  @ManyToMany(mappedBy = "animaisAlimentados")
  private List<Estoque> alimentos = new ArrayList<>();

  public void atualizarStatus(StatusAdocao status) {
    this.statusAdocao = status;
  }

  public void registrarSaida() {
    this.statusAdocao = StatusAdocao.ADOTADO;
  }

}
