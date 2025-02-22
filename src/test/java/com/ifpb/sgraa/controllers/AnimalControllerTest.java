package com.ifpb.sgraa.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ifpb.sgraa.enums.StatusAdocao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ifpb.sgraa.enums.EspecieAnimal;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.services.AnimalService;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

  @Mock
  private AnimalService animalService;

  @InjectMocks
  private AnimalController animalController;

  private Animal animal;
  private Animal animal2;
  private Animal animal3;
  private Animal animal4;

  @BeforeEach
  void setUp() {
    animal = new Animal();
    animal.setId(1L);
    animal.setNome("Tiao");
    animal.setEspecie(EspecieAnimal.CAO);
    animal.setStatusAdocao(StatusAdocao.DISPONIVEL);

    animal2 = new Animal();
    animal2.setId(2L);
    animal2.setNome("Michel Teló");
    animal2.setEspecie(EspecieAnimal.GATO);
    animal2.setStatusAdocao(StatusAdocao.ADOTADO);


    animal3 = new Animal();
    animal3.setId(3L);
    animal3.setNome("Chico");
    animal3.setStatusAdocao(StatusAdocao.DISPONIVEL);


    animal4 = new Animal();
    animal4.setId(4L);
    animal4.setNome(null);
    animal4.setEspecie(EspecieAnimal.CAO);
    animal4.setStatusAdocao(StatusAdocao.DISPONIVEL);


  }

  // Cenario 1
  // Teste 1
  @Test
  void animalDadosCompletos() {
    when(animalService.criarAnimal(any(Animal.class))).thenReturn(animal);

    ResponseEntity<Animal> response = animalController.criarAnimal(animal);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Tiao", response.getBody().getNome());
  }

  // Teste 2
  @Test
  public void deveCadastrarAnimalERetornarComIdGerado() {
    when(animalService.criarAnimal(any(Animal.class))).thenReturn(animal2);

    ResponseEntity<Animal> response = animalController.criarAnimal(animal2);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody().getId());
    assertEquals(2L, response.getBody().getId());
  }

  // Teste 3
  @Test
  public void deveCadastrarAnimalComDadosAdicionais() {
    animal.setIdade(2);
    animal.setRaca("VIRA-LATA");
    when(animalService.criarAnimal(any(Animal.class))).thenReturn(animal);

    ResponseEntity<Animal> response = animalController.criarAnimal(animal);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(2, response.getBody().getIdade());
    assertEquals("VIRA-LATA", response.getBody().getRaca());
  }

  // Cenario 2
  // Teste 1
  @Test
  void deveLancarExcecaoQuandoAnimalForNulo() {
    when(animalService.criarAnimal(null)).thenThrow(new IllegalArgumentException("Animal não pode ser nulo"));

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      animalService.criarAnimal(null);
    });

    assertEquals("Animal não pode ser nulo", exception.getMessage());
  }

  // Teste 2
  @Test
  void deveLancarExcecaoQuandoNomeVazio() {
    animal.setNome("");
    when(animalService.criarAnimal(any(Animal.class))).thenThrow(new IllegalArgumentException("Nome não pode ser vazio"));

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      animalService.criarAnimal(animal);
    });

    assertEquals("Nome não pode ser vazio", exception.getMessage());
    }

  //Teste 3
  @Test
  void deveRejeitarCadastroComEspecieNula() {

    when(animalService.criarAnimal(any(Animal.class))).thenThrow(new IllegalArgumentException("Especie não pode ser vazio"));
    Exception exception = assertThrows(Exception.class, () -> {
      animalController.criarAnimal(animal);
    });

    assertEquals("Especie não pode ser vazio", exception.getMessage());
  }

  // Teste 4
  @Test
  void deveRejeitarCadastroComNomeNulo() {

    when(animalService.criarAnimal(any(Animal.class))).thenThrow(new IllegalArgumentException("Nome não pode ser nulo"));

    Exception exception = assertThrows(Exception.class, () -> {animalController.criarAnimal(animal4);});

    assertEquals("Nome não pode ser nulo", exception.getMessage());
  }


  @Test
  void retornarDisponiveis() {
      when(animalService.buscarAnimaisPorStatus(StatusAdocao.DISPONIVEL))
              .thenReturn(Arrays.asList(animal, animal2));

      ResponseEntity<List<Animal>> response = animalController.listarAnimaisDisponiveis();

      assertNotNull(response.getBody());
      assertEquals(2, response.getBody().size());
      assertEquals("Tiao", response.getBody().get(0).getNome());
      assertEquals("Michel Teló", response.getBody().get(1).getNome());
    }

  }
