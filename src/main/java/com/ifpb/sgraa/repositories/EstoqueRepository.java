package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByDataValidadeBefore(Date data);
    List<Estoque> findByItemContainingIgnoreCase(String item);
}

