package com.ifpb.sgraa.models;

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
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date data;

    private String tipoItem;
    private int quantidade;

    @OneToMany(mappedBy = "doacao", cascade = CascadeType.ALL)
    private List<Estoque> itensDoados = new ArrayList<>();
}
