package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.models.Voluntario;
import com.ifpb.sgraa.repositories.ResgateRepository;
import com.ifpb.sgraa.repositories.VoluntarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoluntarioService {
    private final VoluntarioRepository voluntarioRepository;
    private final ResgateRepository resgateRepository;

    public Voluntario cadastrarVoluntario(Voluntario voluntario) {
        return voluntarioRepository.save(voluntario);
    }

    public Resgate registrarResgate(Long voluntarioId, Resgate resgate) {
        Voluntario voluntario = voluntarioRepository.findById(voluntarioId)
                .orElseThrow(() -> new EntityNotFoundException("Voluntário não encontrado"));

        resgate.setVoluntario(voluntario);
        return resgateRepository.save(resgate);
    }

    public List<Resgate> listarResgatesPorVoluntario(Long voluntarioId) {
        return resgateRepository.findByVoluntarioId(voluntarioId);
    }
}
