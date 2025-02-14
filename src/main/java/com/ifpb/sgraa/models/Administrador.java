package com.ifpb.sgraa.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  public void aprovarAdocao(Long idAdocao) {
  }

  public void reprovarAdocao(Long idAdocao) {
  }

}
