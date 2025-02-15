package com.ifpb.sgraa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Animal> animaisCadastrados;
    @OneToMany
    private List<Voluntario> voluntariosAtivos;
    @OneToMany
    private List<Adocao> processosAdocao;

    public void registrarAnimal(Animal novoAnimal) {
        animaisCadastrados.add(novoAnimal);
    }

    public void gerarRelatorioMensal() {}
}