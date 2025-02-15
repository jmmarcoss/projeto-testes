package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Estoque;
import com.ifpb.sgraa.services.EstoqueService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping("/{doacaoId}")
    public ResponseEntity<Estoque> registrarEntrada(
            @RequestBody Estoque estoque,
            @PathVariable Long doacaoId) {
        try {
            Estoque registro = estoqueService.registrarEntradaEstoque(estoque, doacaoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(registro);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Estoque> atualizarQuantidade(
            @PathVariable Long itemId,
            @RequestParam int quantidade) {
        try {
            Estoque atualizado = estoqueService.atualizarEstoque(itemId, quantidade);
            return ResponseEntity.ok(atualizado);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/alertas-validade")
    public ResponseEntity<List<Estoque>> listarProximosExpirados(
            @RequestParam(defaultValue = "7") int dias) {
        return ResponseEntity.ok(estoqueService.listarProximosDaValidade(dias));
    }
}
