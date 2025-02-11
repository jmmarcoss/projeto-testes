package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Estoque;
import com.ifpb.sgraa.services.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController {
    private final EstoqueService estoqueService;

    @PatchMapping("/{id}/quantidade")
    public ResponseEntity<Estoque> atualizarQuantidade(
            @PathVariable Long id,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.atualizarEstoque(id, quantidade));
    }

    @GetMapping("/alertas")
    public ResponseEntity<List<Estoque>> listarProximosDaValidade(
            @RequestParam(defaultValue = "7") int dias
    ) {
        return ResponseEntity.ok(estoqueService.listarProximosDaValidade(dias));
    }
}
