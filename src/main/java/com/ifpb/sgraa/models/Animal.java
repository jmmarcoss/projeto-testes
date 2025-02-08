package com.ifpb.sgraa.models;

import com.ifpb.sgraa.enums.StatusAdocao;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especie;
    private String raca;
    private Integer idade;

    @Enumerated(EnumType.STRING)
    private StatusAdocao statusAdocao;

    private LocalDate dataEntrada;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<TratamentoMedico> tratamentos = new ArrayList<>();

    @OneToOne(mappedBy = "animal")
    private Adocao adocao;
}

