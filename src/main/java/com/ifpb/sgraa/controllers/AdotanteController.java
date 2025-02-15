package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Adocao;
import com.ifpb.sgraa.models.Adotante;
import com.ifpb.sgraa.services.AdotanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adotantes")
@RequiredArgsConstructor
public class AdotanteController {

    private final AdotanteService adotanteService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Adotante adotante) {
        try {
            Adotante saved = adotanteService.cadastrarAdotante(adotante);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adotante> buscarPorId(@PathVariable Long id) {
        return adotanteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/adocoes")
    public ResponseEntity<List<Adocao>> listarAdocoes(@PathVariable Long id) {
        return ResponseEntity.ok(adotanteService.listarAdocoes(id));
    }
}

