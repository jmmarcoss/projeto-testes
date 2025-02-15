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
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Animal> animaisCadastrados = new ArrayList<>();
    @OneToMany
    private List<Voluntario> voluntariosAtivos = new ArrayList<>();
    @OneToMany
    private List<Adocao> processosAdocao = new ArrayList<>();

    public void registrarAnimal(Animal novoAnimal) {
        animaisCadastrados.add(novoAnimal);
    }

    public void gerarRelatorioMensal() {}
}