package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Doacao;
import com.ifpb.sgraa.models.Estoque;
import com.ifpb.sgraa.repositories.DoacaoRepository;
import com.ifpb.sgraa.repositories.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final DoacaoRepository doacaoRepository;

    public Estoque registrarEntradaEstoque(Estoque estoque, Long doacaoId) {
        Doacao doacao = doacaoRepository.findById(doacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Doação não encontrada"));
        estoque.setDoacao(doacao);
        return estoqueRepository.save(estoque);
    }

    public void emitirAlertasValidade() {
        List<Estoque> expirados = estoqueRepository.findByDataValidadeBefore(new Date());
        // Implementar lógica de notificação
    }
}
