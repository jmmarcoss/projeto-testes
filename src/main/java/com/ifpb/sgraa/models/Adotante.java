package com.ifpb.sgraa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.ifpb.sgraa.enums.StatusAdocao;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adotante {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true)
  private String cpf;

  private String endereco;
  private String contato;

  @OneToMany(mappedBy = "adotante")
  private List<Adocao> adocoes = new ArrayList<>();

  public Adocao solicitarAdocao(Animal animal) {
    if (animal.getStatusAdocao() == StatusAdocao.INDISPONIVEL)
      return null;
    Adocao adocao = new Adocao();
    adocao.setAdotante(this);
    adocao.setAnimal(animal);
    animal.setStatusAdocao(StatusAdocao.SOLICITADO);
    adocoes.add(adocao);
    return adocao;
  }

  }
