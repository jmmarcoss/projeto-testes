package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.models.Voluntario;
import com.ifpb.sgraa.repositories.ResgateRepository;
import com.ifpb.sgraa.repositories.VoluntarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResgateService {
    private final ResgateRepository resgateRepository;
    private final VoluntarioRepository voluntarioRepository;

    public Resgate registrarResgate(Resgate resgate, Long voluntarioId) {
        Voluntario voluntario = voluntarioRepository.findById(voluntarioId)
                .orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));
        resgate.setVoluntario(voluntario);
        return resgateRepository.save(resgate);
    }
}
