package com.ifpb.sgraa.models;

import com.ifpb.sgraa.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
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
  private List<SolicitacaoAdocao> solicitacoes = new ArrayList<>();

  public SolicitacaoAdocao solicitarAdocao(Animal animal) {

    if(animal.getStatusAdocao() == StatusAdocao.ADOTADO ||
      animal.getStatusAdocao() == StatusAdocao.INDISPONIVEL
    ) { return null; }

    SolicitacaoAdocao solicitacao = new SolicitacaoAdocao();
    solicitacao.setAnimal(animal);
    solicitacao.setAdotante(this);
    solicitacao.setDataSolicitacao(new Date());
    solicitacao.setStatus(StatusSolicitacao.PENDENTE);
    solicitacoes.add(solicitacao);

    return solicitacao;
  }

  }
