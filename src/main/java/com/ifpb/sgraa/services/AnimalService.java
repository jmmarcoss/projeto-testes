package com.ifpb.sgraa.services;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.hibernate.Hibernate;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository animalRepository;

    public Animal cadastrarAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    //TODO: create DTO entity
    @Transactional
    public List<Animal> listarAnimals() {
        List<Animal> animais = animalRepository.findAll();
        animais.forEach(animal -> Hibernate.initialize(animal.getTratamentos()));
        return animais;
    }

    public Animal atualizarStatus(Long id, StatusAdocao status) {
        Animal animal = animalRepository.findById(id).orElseThrow();
        animal.setStatusAdocao(status);
        return animalRepository.save(animal);
    }
}

