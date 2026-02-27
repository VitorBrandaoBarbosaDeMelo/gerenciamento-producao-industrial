package com.factory.service;

import com.factory.model.MateriaPrima;
import com.factory.repository.MateriaPrimaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MateriaPrimaServiceTest {
    
    @Mock
    private MateriaPrimaRepository repository;
    
    @InjectMocks
    private MateriaPrimaService service;
    
    private MateriaPrima materiaPrima;
    
    @BeforeEach
    void setUp() {
        materiaPrima = new MateriaPrima(1L, "MP001", "Aço", 1000.0);
    }
    
    @Test
    void findAll_DeveRetornarListaDeMateriaPrimas() {
        List<MateriaPrima> materiasPrimas = Arrays.asList(materiaPrima);
        when(repository.findAll()).thenReturn(materiasPrimas);
        
        List<MateriaPrima> result = service.findAll();
        
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }
    
    @Test
    void findById_DeveRetornarMateriaPrima() {
        when(repository.findById(1L)).thenReturn(Optional.of(materiaPrima));
        
        MateriaPrima result = service.findById(1L);
        
        assertNotNull(result);
        assertEquals("MP001", result.getCodigo());
        verify(repository, times(1)).findById(1L);
    }
    
    @Test
    void create_DeveCriarMateriaPrima() {
        when(repository.existsByCodigo("MP001")).thenReturn(false);
        when(repository.save(any(MateriaPrima.class))).thenReturn(materiaPrima);
        
        MateriaPrima result = service.create(materiaPrima);
        
        assertNotNull(result);
        assertEquals("MP001", result.getCodigo());
        verify(repository, times(1)).save(materiaPrima);
    }
    
    @Test
    void create_DeveLancarExcecaoSeCodigoJaExiste() {
        when(repository.existsByCodigo("MP001")).thenReturn(true);
        
        assertThrows(RuntimeException.class, () -> service.create(materiaPrima));
        verify(repository, never()).save(any());
    }
    
    @Test
    void update_DeveAtualizarMateriaPrima() {
        MateriaPrima updated = new MateriaPrima(1L, "MP001", "Aço Inox", 1500.0);
        when(repository.findById(1L)).thenReturn(Optional.of(materiaPrima));
        when(repository.existsByCodigo("MP001")).thenReturn(false);
        when(repository.save(any(MateriaPrima.class))).thenReturn(updated);
        
        MateriaPrima result = service.update(1L, updated);
        
        assertNotNull(result);
        assertEquals("Aço Inox", result.getNome());
        verify(repository, times(1)).save(any(MateriaPrima.class));
    }
    
    @Test
    void delete_DeveRemoverMateriaPrima() {
        when(repository.findById(1L)).thenReturn(Optional.of(materiaPrima));
        doNothing().when(repository).deleteById(1L);
        
        service.delete(1L);
        
        verify(repository, times(1)).deleteById(1L);
    }
}
