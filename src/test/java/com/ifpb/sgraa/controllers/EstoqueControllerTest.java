package com.ifpb.sgraa.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.ifpb.sgraa.models.Doacao;
import com.ifpb.sgraa.models.Estoque;
import com.ifpb.sgraa.services.EstoqueService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class EstoqueControllerTest {

    @Mock
    private EstoqueService estoqueService;

    @InjectMocks
    private EstoqueController estoqueController;

    private Estoque estoque;


    @BeforeEach
    void setUp() {
        Doacao doacao = new Doacao();
        doacao.setId(1L);
        estoque = new Estoque();
        estoque.setId(1L);
        estoque.setItem("Racao");
        estoque.setQuantidade(50);
    }

    @Test
    void deveRegistrarEntradaNoEstoqueComSucesso() {
        when(estoqueService.registrarEntradaEstoque(any(Estoque.class), eq(1L))).thenReturn(estoque);

        ResponseEntity<Estoque> response = estoqueController.registrarEntrada(estoque, 1L);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getQuantidade());
    }

    @Test
    void deveLancarErroQuandoDoacaoNaoExiste() {
        when(estoqueService.registrarEntradaEstoque(any(Estoque.class), eq(999L)))
                .thenThrow(new EntityNotFoundException("Doação não encontrada"));

        ResponseEntity<Estoque> response = estoqueController.registrarEntrada(estoque, 999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deveAtualizarQuantidadeDoEstoqueComSucesso() {
        when(estoqueService.atualizarEstoque(1L, 30)).thenReturn(estoque);

        ResponseEntity<Estoque> response = estoqueController.atualizarQuantidade(1L, 30);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(50, response.getBody().getQuantidade());
    }

    @Test
    void deveLancarErroQuandoItemNaoExiste() {
        when(estoqueService.atualizarEstoque(eq(999L), eq(30)))
                .thenThrow(new EntityNotFoundException("Item não encontrado"));

        ResponseEntity<Estoque> response = estoqueController.atualizarQuantidade(999L, 30);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deveLancarErroQuandoQuantidadeForNegativa() {
        when(estoqueService.atualizarEstoque(eq(1L), eq(-5)))
                .thenThrow(new IllegalArgumentException("Quantidade não pode ser negativa"));

        ResponseEntity<Estoque> response = estoqueController.atualizarQuantidade(1L, -5);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deveListarItensProximosDaValidade() {
        when(estoqueService.listarProximosDaValidade(7)).thenReturn(Arrays.asList(estoque));

        ResponseEntity<List<Estoque>> response = estoqueController.listarProximosExpirados(7);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

}
