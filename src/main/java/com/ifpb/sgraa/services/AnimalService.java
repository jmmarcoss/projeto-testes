package com.ifpb.sgraa.services;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.repositories.AnimalRepository;
import com.ifpb.sgraa.repositories.ResgateRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
  private final AnimalRepository animalRepository;
  private final ResgateRepository resgateRepository;

  public Animal criarAnimal(Animal animal) {
    return animalRepository.save(animal);
  }

  public List<Animal> buscarAnimaisPorStatus(StatusAdocao status) {
    return animalRepository.findByStatusAdocao(status);
  }

  public List<Animal> listarAnimais() {
    return animalRepository.findAll();
  }

  public Optional<Animal> buscarAnimalPorId(Long id) {
    return animalRepository.findById(id);
  }

  public Animal cadastrarAnimal(Animal animal) {
    return animalRepository.save(animal);
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
