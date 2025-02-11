package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    List<Adocao> findByTermoAssinado(boolean termoAssinado);
    boolean existsByAnimalId(Long animalId);
}
