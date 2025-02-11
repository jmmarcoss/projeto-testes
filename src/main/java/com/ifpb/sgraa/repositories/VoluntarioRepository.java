package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    List<Voluntario> findByFuncao(String funcao);
}
