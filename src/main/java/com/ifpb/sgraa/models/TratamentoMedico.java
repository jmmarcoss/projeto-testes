package com.ifpb.sgraa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TratamentoMedico {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Temporal(TemporalType.DATE)
  private Date data;

  @ElementCollection
  private List<String> medicacoes;
  private String procedimento;

  @ManyToOne
  @JoinColumn(name = "veterinario_id")
  private Veterinario veterinario;

  @ManyToOne
  @JoinColumn(name = "animal_id")
  private Animal animal;

}
