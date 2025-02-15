package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.models.Voluntario;
import com.ifpb.sgraa.services.VoluntarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voluntarios")
@RequiredArgsConstructor
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    @PostMapping
    public ResponseEntity<Voluntario> cadastrarVoluntario(@RequestBody Voluntario voluntario) {
        Voluntario salvo = voluntarioService.cadastrarVoluntario(voluntario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/{voluntarioId}/resgates")
    public ResponseEntity<Resgate> registrarResgate(
            @PathVariable Long voluntarioId,
            @RequestBody Resgate resgate) {
        try {
            Resgate resgateRegistrado = voluntarioService.registrarResgate(voluntarioId, resgate);
            return ResponseEntity.status(HttpStatus.CREATED).body(resgateRegistrado);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{voluntarioId}/resgates")
    public ResponseEntity<List<Resgate>> listarResgates(
            @PathVariable Long voluntarioId) {
        return ResponseEntity.ok(voluntarioService.listarResgatesPorVoluntario(voluntarioId));
    }
}
