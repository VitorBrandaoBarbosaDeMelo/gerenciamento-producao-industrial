package com.factory.controller;

import com.factory.dto.SugestaoProducaoDTO;
import com.factory.service.ProducaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producao")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProducaoController {
    
    private final ProducaoService service;
    
    @GetMapping("/otimizar")
    public ResponseEntity<List<SugestaoProducaoDTO>> otimizarProducao() {
        return ResponseEntity.ok(service.calcularOtimizacao());
    }
}
