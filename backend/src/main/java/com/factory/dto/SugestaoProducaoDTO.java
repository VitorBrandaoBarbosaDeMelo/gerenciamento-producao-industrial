package com.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SugestaoProducaoDTO {
    private Long produtoId;
    private String produtoCodigo;
    private String produtoNome;
    private Double valorUnitario;
    private Integer quantidadeProduzir;
    private Double valorTotal;
}
