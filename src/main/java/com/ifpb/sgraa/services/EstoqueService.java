package com.ifpb.sgraa.services;

import com.ifpb.sgraa.models.Doacao;
import com.ifpb.sgraa.models.Estoque;
import com.ifpb.sgraa.repositories.DoacaoRepository;
import com.ifpb.sgraa.repositories.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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

    public Estoque atualizarEstoque(Long itemId, int novaQuantidade) {
        Estoque estoque = estoqueRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }

        estoque.setQuantidade(novaQuantidade);
        return estoqueRepository.save(estoque);
    }

    public List<Estoque> listarProximosDaValidade(int dias) {
        Date dataLimite = new Date(System.currentTimeMillis() + (dias * 86400000L));
        return estoqueRepository.findByDataValidadeBefore(dataLimite);
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void emitirAlertasValidade() {
        List<Estoque> expirados = estoqueRepository.findByDataValidadeBefore(new Date());
        expirados.forEach(item ->
                System.out.println("Alerta: Item " + item.getItem() + " expirado em " + item.getDataValidade())
        );
    }
}
