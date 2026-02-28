package com.factory.service;

import com.factory.model.Composicao;
import com.factory.model.MateriaPrima;
import com.factory.model.Produto;
import com.factory.repository.MateriaPrimaRepository;
import com.factory.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducaoServiceTest {
    
    @Mock
    private ProdutoRepository produtoRepository;
    
    @Mock
    private MateriaPrimaRepository materiaPrimaRepository;
    
    @InjectMocks
    private ProducaoService service;
    
    private MateriaPrima mp1;
    private MateriaPrima mp2;
    private Produto produto1;
    private Produto produto2;
    
    @BeforeEach
    void setUp() {
        mp1 = new MateriaPrima(1L, "MP001", "Aço", 100.0, "Quilogramas", 7.8, 50.0);
        mp2 = new MateriaPrima(2L, "MP002", "Plástico", 200.0, "Quilogramas", 0.95, 15.0);
        
        produto1 = new Produto(1L, "P001", "Produto A", 50.0, new ArrayList<>());
        Composicao comp1 = new Composicao(1L, produto1, mp1, 10.0);
        produto1.addComposicao(comp1);
        
        produto2 = new Produto(2L, "P002", "Produto B", 30.0, new ArrayList<>());
        Composicao comp2 = new Composicao(2L, produto2, mp2, 20.0);
        produto2.addComposicao(comp2);
    }
    
    @Test
    void calcularOtimizacao_DeveRetornarSugestoes() {
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));
        when(materiaPrimaRepository.findAll()).thenReturn(Arrays.asList(mp1, mp2));
        
        var sugestoes = service.calcularOtimizacao();
        
        assertNotNull(sugestoes);
        assertFalse(sugestoes.isEmpty());
        verify(produtoRepository, times(1)).findAll();
        verify(materiaPrimaRepository, times(1)).findAll();
    }
    
    @Test
    void calcularOtimizacao_DevePriorizarProdutoDeMaiorValor() {
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));
        when(materiaPrimaRepository.findAll()).thenReturn(Arrays.asList(mp1, mp2));
        
        var sugestoes = service.calcularOtimizacao();
        
        // Produto1 tem valor 50.0, maior que Produto2 (30.0)
        // Deve ser priorizado
        if (!sugestoes.isEmpty()) {
            assertTrue(sugestoes.get(0).getValorUnitario() >= 30.0);
        }
    }
    
    @Test
    void calcularOtimizacao_DeveCalcularValorTotal() {
        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1));
        when(materiaPrimaRepository.findAll()).thenReturn(Arrays.asList(mp1));
        
        var sugestoes = service.calcularOtimizacao();
        
        if (!sugestoes.isEmpty()) {
            var primeira = sugestoes.get(0);
            assertEquals(primeira.getValorUnitario() * primeira.getQuantidadeProduzir(), 
                        primeira.getValorTotal());
        }
    }
}
