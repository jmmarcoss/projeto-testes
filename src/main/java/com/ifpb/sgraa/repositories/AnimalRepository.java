package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByStatusAdocao(StatusAdocao status);
    List<Animal> findByResgateId(Long resgateId);
}

