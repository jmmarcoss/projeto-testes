package com.ifpb.sgraa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.models.TratamentoMedico;
import com.ifpb.sgraa.models.Veterinario;
import com.ifpb.sgraa.repositories.AnimalRepository;
import com.ifpb.sgraa.repositories.VeterinarioRepository;

@Service
public class VeterinarioService {

  @Autowired
  private VeterinarioRepository veterinarioRepository;

  @Autowired
  private AnimalRepository animalRepository;

  public Veterinario cadastrarVeterinario(Veterinario veterinario) {
    return veterinarioRepository.save(veterinario);
  }

  public TratamentoMedico prescreverTratamento(Long veterinarioId, Long animalId, List<String> medicacoes) {
    Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
        .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));
    Animal animal = animalRepository.findById(animalId)
        .orElseThrow(() -> new RuntimeException("Animal não encontrado"));

    return veterinario.prescreverTratamento(animal, medicacoes);
  }
}
