package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Adotante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdotanteRepository extends JpaRepository<Adotante, Long> {
    Optional<Adotante> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}
