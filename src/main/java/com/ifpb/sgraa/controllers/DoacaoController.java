package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Doacao;
import com.ifpb.sgraa.services.DoacaoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/doacoes")
@RequiredArgsConstructor
public class DoacaoController {

    private final DoacaoService doacaoService;

    @PostMapping
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao) {
        Doacao novaDoacao = doacaoService.salvarDoacao(doacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);
    }

    @GetMapping
    public ResponseEntity<List<Doacao>> listarDoacoes() {
        return ResponseEntity.ok(doacaoService.listarDoacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Long id) {
        return doacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Doacao>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        return ResponseEntity.ok(doacaoService.buscarPorPeriodo(inicio, fim));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id) {
        try {
            doacaoService.excluirDoacao(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
