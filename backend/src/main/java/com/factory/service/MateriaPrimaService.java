package com.factory.service;

import com.factory.model.MateriaPrima;
import com.factory.repository.MateriaPrimaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaPrimaService {
    
    private final MateriaPrimaRepository repository;
    
    public List<MateriaPrima> findAll() {
        return repository.findAll();
    }
    
    public MateriaPrima findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada"));
    }
    
    @Transactional
    public MateriaPrima create(MateriaPrima materiaPrima) {
        if (repository.existsByCodigo(materiaPrima.getCodigo())) {
            throw new RuntimeException("Já existe uma matéria-prima com este código");
        }
        return repository.save(materiaPrima);
    }
    
    @Transactional
    public MateriaPrima update(Long id, MateriaPrima materiaPrima) {
        MateriaPrima existing = findById(id);
        
        if (!existing.getCodigo().equals(materiaPrima.getCodigo()) 
                && repository.existsByCodigo(materiaPrima.getCodigo())) {
            throw new RuntimeException("Já existe uma matéria-prima com este código");
        }
        
        existing.setCodigo(materiaPrima.getCodigo());
        existing.setNome(materiaPrima.getNome());
        existing.setQuantidadeEstoque(materiaPrima.getQuantidadeEstoque());
        
        return repository.save(existing);
    }
    
    @Transactional
    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }
}
