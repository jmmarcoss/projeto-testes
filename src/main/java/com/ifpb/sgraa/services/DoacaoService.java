package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Doacao;
import com.ifpb.sgraa.repositories.DoacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final DoacaoRepository doacaoRepository;
    private final EstoqueService estoqueService;

    public Doacao salvarDoacao(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> listarDoacoes() {
        return doacaoRepository.findAll();
    }

    public Optional<Doacao> buscarPorId(Long id) {
        return doacaoRepository.findById(id);
    }

    public List<Doacao> buscarPorPeriodo(Date inicio, Date fim) {
        return doacaoRepository.findByDataBetween(inicio, fim);
    }

    public void excluirDoacao(Long id) {
        if (!doacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Doação não encontrada");
        }
        doacaoRepository.deleteById(id);
    }
}
