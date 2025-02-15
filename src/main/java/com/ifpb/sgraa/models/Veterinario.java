package com.ifpb.sgraa.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  private String crm;

  @OneToMany(mappedBy = "veterinario")
  private List<TratamentoMedico> tratamentos;

  public TratamentoMedico prescreverTratamento(Animal animal, List<String> medicacoes, String procedimento) {
    TratamentoMedico tratamento = new TratamentoMedico();
    tratamento.setAnimal(animal);
    tratamento.setMedicacoes(medicacoes);
    tratamento.setData(new Date());
    tratamento.setProcedimento(procedimento);
    tratamento.setVeterinario(this);
    return tratamento;
  }

}
