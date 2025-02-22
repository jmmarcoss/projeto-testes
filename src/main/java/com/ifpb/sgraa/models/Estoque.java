package com.ifpb.sgraa.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private int quantidade;

    @Temporal(TemporalType.DATE)
    private Date dataValidade;

    @ManyToOne
    @JoinColumn(name = "doacao_id")
    @Column(nullable = false)
    private Doacao doacao;

    @ManyToMany
    @JoinTable(
            name = "animal_alimentacao",
            joinColumns = @JoinColumn(name = "estoque_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<Animal> animaisAlimentados = new ArrayList<>();;
}
