package com.ifpb.sgraa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voluntario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  private String contato;
  private String funcao;

  public Resgate iniciarResgate(String local) {
    Resgate resgate = new Resgate();
    resgate.setLocal(local);
    resgate.setVoluntario(this);
    return resgate;
  }

  @OneToMany(mappedBy = "voluntario")
  private List<Resgate> resgates = new ArrayList<>();

}
