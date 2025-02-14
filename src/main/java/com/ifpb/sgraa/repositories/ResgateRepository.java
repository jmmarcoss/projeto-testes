package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.Resgate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResgateRepository extends JpaRepository<Resgate, Long> {
    List<Resgate> findByVoluntarioId(Long voluntarioId);
}
