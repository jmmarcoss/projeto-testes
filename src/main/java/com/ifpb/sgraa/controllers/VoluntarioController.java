package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.models.Voluntario;
import com.ifpb.sgraa.services.VoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voluntarios")
@RequiredArgsConstructor
public class VoluntarioController {
    private final VoluntarioService voluntarioService;

    @PostMapping
    public ResponseEntity<Voluntario> cadastrar(@RequestBody Voluntario voluntario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voluntarioService.cadastrarVoluntario(voluntario));
    }

    @PostMapping("/{voluntarioId}/resgates")
    public ResponseEntity<Resgate> registrarResgate(
            @PathVariable Long voluntarioId,
            @RequestBody Resgate resgate
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voluntarioService.registrarResgate(voluntarioId, resgate));
    }
}
