package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Adocao;
import com.ifpb.sgraa.services.AdocaoService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adocoes")
@RequiredArgsConstructor
public class AdocaoController {

    private final AdocaoService adocaoService;

    @PostMapping
    public ResponseEntity<Adocao> solicitarAdocao(
            @RequestBody SolicitarAdocaoRequest request) {
        Adocao novaAdocao = adocaoService.solicitarAdocao(
                request.adotanteId(),
                request.animalId()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novaAdocao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAdocao(@PathVariable Long id) {
        adocaoService.cancelarAdocao(id);
        return ResponseEntity.noContent().build();
    }

    public record SolicitarAdocaoRequest(
            @NotNull Long adotanteId,
            @NotNull Long animalId
    ) {}
}
