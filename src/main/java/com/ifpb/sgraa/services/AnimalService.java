package com.ifpb.sgraa.services;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.repositories.AnimalRepository;
import com.ifpb.sgraa.repositories.ResgateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.hibernate.Hibernate;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final ResgateRepository resgateRepository;

    public Animal criarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public List<Animal> listAnimals() {
        return animalRepository.findAll();
    }

    public Animal atualizarStatus(Long id, StatusAdocao status) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));
        animal.setStatusAdocao(status);
        return animalRepository.save(animal);
    }

    public void vincularResgate(Long animalId, Long resgateId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));
        Resgate resgate = resgateRepository.findById(resgateId)
                .orElseThrow(() -> new EntityNotFoundException("Resgate não encontrado"));

        animal.setResgate(resgate);
        animalRepository.save(animal);
    }
}

