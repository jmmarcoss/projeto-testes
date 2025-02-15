package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> criarAnimal(@RequestBody Animal animal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(animalService.criarAnimal(animal));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listarTodos() {
        return ResponseEntity.ok(animalService.listarAnimais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable Long id) {
        return animalService.buscarAnimalPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Animal> atualizarStatus(
            @PathVariable Long id,
            @RequestBody StatusAdocao status) {
        return ResponseEntity.ok(
                animalService.atualizarStatus(id, StatusAdocao.valueOf(status.toString()))
        );
    }

    @PutMapping("/{animalId}/resgate")
    public ResponseEntity<Void> vincularResgate(
            @PathVariable Long animalId,
            @RequestBody Long resgateId) {
        animalService.vincularResgate(animalId, resgateId);
        return ResponseEntity.noContent().build();
    }
}
