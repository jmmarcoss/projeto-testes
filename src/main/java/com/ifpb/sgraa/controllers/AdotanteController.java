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
    public ResponseEntity<Adotante> cadastrar(@RequestBody Adotante adotante) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adotanteService.cadastrarAdotante(adotante));
    }

    @GetMapping("/{id}/adocoes")
    public ResponseEntity<List<Adocao>> listarAdocoes(@PathVariable Long id) {
        return ResponseEntity.ok(adotanteService.listarAdocoes(id));
    }
}
