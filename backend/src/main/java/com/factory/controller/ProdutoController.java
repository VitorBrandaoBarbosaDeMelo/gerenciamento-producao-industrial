package com.factory.controller;

import com.factory.dto.ProdutoDTO;
import com.factory.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    private final ProdutoService service;
    
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@Valid @RequestBody ProdutoDTO produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(produto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, 
                                              @Valid @RequestBody ProdutoDTO produto) {
        return ResponseEntity.ok(service.update(id, produto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
