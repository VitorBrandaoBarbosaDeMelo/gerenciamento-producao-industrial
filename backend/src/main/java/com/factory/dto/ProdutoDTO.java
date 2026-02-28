package com.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Long id;
    private String codigo;
    private String nome;
    private Double valor;
    private Double margemLucro;
    private Double custoTotal;
    private List<ComposicaoDTO> composicao;
}
