package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.repositories.AnimalRepository;
import com.ifpb.sgraa.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<Animal> cadastrar(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.cadastrarAnimal(animal));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listar() {
        return ResponseEntity.ok(animalService.listarAnimals());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Animal> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusAdocao status
    ) {
        return ResponseEntity.ok(animalService.atualizarStatus(id, status));
    }
}
