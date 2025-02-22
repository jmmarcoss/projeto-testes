package com.ifpb.sgraa.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ifpb.sgraa.enums.EspecieAnimal;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.models.Resgate;
import com.ifpb.sgraa.services.ResgateService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ResgateControllerTest {

    @Mock
    private ResgateService resgateService;

    @InjectMocks
    private ResgateController resgateController;

    private Resgate resgate;

    @BeforeEach
    void setUp() {

        List<Animal> animais = new ArrayList<>();
        Animal animal = new Animal();
        animal.setNome("Filo");
        animal.setId(1L);
        animal.setRaca("VIRA-LATA");
        animal.setEspecie(EspecieAnimal.CAO);
        animais.add(animal);


        resgate = new Resgate();
        resgate.setId(1L);
        resgate.setAnimaisResgatados(animais);
    }

    @Test
    void deveRegistrarResgateComSucesso() {
        when(resgateService.registrarResgate(any(Resgate.class), eq(1L))).thenReturn(resgate);

        ResponseEntity<Resgate> response = resgateController.registrarResgate(resgate, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertNotNull(response.getBody());
        assertEquals("Filo", response.getBody().getAnimaisResgatados().getFirst().getNome());
        assertEquals(1, response.getBody().getAnimaisResgatados().size());
    }

    @Test
    void deveLancarErroQuandoVoluntarioNaoExiste() {
        when(resgateService.registrarResgate(any(Resgate.class), eq(999L)))
                .thenThrow(new EntityNotFoundException("Voluntário não encontrado"));

        ResponseEntity<Resgate> response = resgateController.registrarResgate(resgate, 999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
