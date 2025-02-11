package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByDataBetween(Date inicio, Date fim);
}
