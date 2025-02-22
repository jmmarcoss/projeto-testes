package com.ifpb.sgraa.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ifpb.sgraa.controllers.VeterinarioController;
import com.ifpb.sgraa.enums.EspecieAnimal;
import com.ifpb.sgraa.enums.StatusAdocao;
import com.ifpb.sgraa.models.Animal;
import com.ifpb.sgraa.models.TratamentoMedico;
import com.ifpb.sgraa.models.Veterinario;
import com.ifpb.sgraa.services.VeterinarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class VeterinarioControllerTest {

    @Mock
    private VeterinarioService veterinarioService;

    @InjectMocks
    private VeterinarioController veterinarioController;

    private TratamentoMedico tratamentoMedico;

    @BeforeEach
    void setUp() {
        Veterinario veterinario = new Veterinario();
        veterinario.setId(1L);
        veterinario.setNome("Livia");
        veterinario.setCrm("12345");

        Animal animal = new Animal();
        animal.setId(1L);
        animal.setNome("Tiao");
        animal.setEspecie(EspecieAnimal.CAO);
        animal.setStatusAdocao(StatusAdocao.DISPONIVEL);


        tratamentoMedico = new TratamentoMedico();
        tratamentoMedico.setId(1L);
        tratamentoMedico.setVeterinario(veterinario);
        tratamentoMedico.setAnimal(animal);
        tratamentoMedico.setMedicacoes(List.of("Antibiótico", "Anti-inflamatório"));
        tratamentoMedico.setProcedimento("Cirurgia");
    }

    @Test
    void devePrescreverTratamentoComSucesso() {
        Long veterinarioId = 1L;
        Long animalId = 1L;
        List<String> medicacoes = List.of("Antibiótico", "Anti-inflamatório");
        String procedimento = "Cirurgia";

        when(veterinarioService.prescreverTratamento(veterinarioId, animalId, medicacoes, procedimento))
                .thenReturn(tratamentoMedico);

        ResponseEntity<TratamentoMedico> response = veterinarioController.prescreverTratamento(
                veterinarioId, animalId, medicacoes, procedimento
        );

        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getVeterinario().getId());
        assertEquals(1L, response.getBody().getAnimal().getId());
        assertEquals("Cirurgia", response.getBody().getProcedimento());
        assertEquals(2, response.getBody().getMedicacoes().size());
    }

    @Test
    void deveLancarExcecaoQuandoVeterinarioNaoEncontrado() {
        Long veterinarioId = 999L;
        Long animalId = 202L;
        List<String> medicacoes = List.of("Analgésico");
        String procedimento = "Exame";

        when(veterinarioService.prescreverTratamento(veterinarioId, animalId, medicacoes, procedimento))
                .thenThrow(new IllegalArgumentException("Veterinário não encontrado"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarioController.prescreverTratamento(veterinarioId, animalId, medicacoes, procedimento);
        });

        assertEquals("Veterinário não encontrado", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoAnimalNaoExiste() {
        when(veterinarioService.prescreverTratamento(101L, 999L, List.of("Analgésico"), "Exame"))
                .thenThrow(new IllegalArgumentException("Animal não encontrado"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarioController.prescreverTratamento(101L, 999L, List.of("Analgésico"), "Exame");
        });

        assertEquals("Animal não encontrado", exception.getMessage());
    }


    @Test
    void deveLancarExcecaoQuandoMedicacoesSaoVazias() {
        Long veterinarioId = 101L;
        Long animalId = 202L;
        List<String> medicacoes = List.of();
        String procedimento = "Vacinação";

        when(veterinarioService.prescreverTratamento(veterinarioId, animalId, medicacoes, procedimento))
                .thenThrow(new IllegalArgumentException("Lista de medicações não pode estar vazia"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarioController.prescreverTratamento(veterinarioId, animalId, medicacoes, procedimento);
        });

        assertEquals("Lista de medicações não pode estar vazia", exception.getMessage());
    }

    @Test
    void deveLancarErroQuandoProcedimentoNaoForInformado() {
        when(veterinarioService.prescreverTratamento(101L, 202L, List.of("Antibiótico"), ""))
                .thenThrow(new IllegalArgumentException("Procedimento não informado"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            veterinarioController.prescreverTratamento(101L, 202L, List.of("Antibiótico"), "");
        });

        assertEquals("Procedimento não informado", exception.getMessage());
    }
}
