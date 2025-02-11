package com.ifpb.sgraa.repositories;

import com.ifpb.sgraa.models.TratamentoMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TratamentoMedicoRepository extends JpaRepository<TratamentoMedico, Long> {
    List<TratamentoMedico> findByAnimalId(Long animalId);
}
