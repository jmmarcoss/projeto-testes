package com.ifpb.sgraa.controllers;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.services.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Animal> cadastrar(@RequestBody Animal animal) {
        return ResponseEntity.ok(animalService.criarAnimal(animal));
    }

    @GetMapping
    public ResponseEntity<List<Animal>> listar() {
        return ResponseEntity.ok(animalService.listAnimals());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Animal> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusAdocao status
    ) {
        return ResponseEntity.ok(animalService.atualizarStatus(id, status));
    }
}
