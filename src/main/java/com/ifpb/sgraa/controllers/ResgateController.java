package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.services.ResgateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resgates")
@RequiredArgsConstructor
public class ResgateController {

    private final ResgateService resgateService;

    @PostMapping("/{voluntarioId}")
    public ResponseEntity<Resgate> registrarResgate(
            @RequestBody Resgate resgate,
            @PathVariable Long voluntarioId) {
        try {
            Resgate registro = resgateService.registrarResgate(resgate, voluntarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(registro);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
