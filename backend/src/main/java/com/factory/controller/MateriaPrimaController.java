package com.factory.controller;

import com.factory.model.MateriaPrima;
import com.factory.service.MateriaPrimaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias-primas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MateriaPrimaController {
    
    private final MateriaPrimaService service;
    
    @GetMapping
    public ResponseEntity<List<MateriaPrima>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrima> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<MateriaPrima> create(@Valid @RequestBody MateriaPrima materiaPrima) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(materiaPrima));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MateriaPrima> update(@PathVariable Long id, 
                                                @Valid @RequestBody MateriaPrima materiaPrima) {
        return ResponseEntity.ok(service.update(id, materiaPrima));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
