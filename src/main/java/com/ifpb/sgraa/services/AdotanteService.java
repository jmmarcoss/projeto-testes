package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Adocao;
import com.ifpb.sgraa.models.Adotante;
import com.ifpb.sgraa.repositories.AdocaoRepository;
import com.ifpb.sgraa.repositories.AdotanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdotanteService {
    private final AdotanteRepository adotanteRepository;
    private final AdocaoRepository adocaoRepository;

    public Adotante cadastrarAdotante(Adotante adotante) {
        if (adotanteRepository.existsByCpf(adotante.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return adotanteRepository.save(adotante);
    }

    public List<Adocao> listarAdocoes(Long adotanteId) {
        return adocaoRepository.findByAdotanteId(adotanteId);
    }

    public Optional<Adotante> buscarPorId(Long id) {
        return adotanteRepository.findById(id);
    }
}
