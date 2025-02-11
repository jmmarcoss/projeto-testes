package com.ifpb.sgraa.services;

import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Adocao;
import com.ifpb.sgraa.models.Adotante;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.repositories.AdocaoRepository;
import com.ifpb.sgraa.repositories.AdotanteRepository;
import com.ifpb.sgraa.repositories.AnimalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final AdotanteRepository adotanteRepository;

    public Adocao solicitarAdocao(Long adotanteId, Long animalId) {
        Adotante adotante = adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new EntityNotFoundException("Adotante não encontrado"));
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new EntityNotFoundException("Animal não encontrado"));

        Adocao adocao = new Adocao();
        adocao.setData(new Date(

        ));
        adocao.setTermoAssinado(false);
        adocao.setAdotante(adotante);
        adocao.setAnimal(animal);

        return adocaoRepository.save(adocao);
    }

    public void cancelarAdocao(Long id) {
        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Adoção não encontrada"));
        adocao.getAnimal().setStatusAdocao(StatusAdocao.DISPONIVEL);
        animalRepository.save(adocao.getAnimal());
        adocaoRepository.delete(adocao);
    }
}
