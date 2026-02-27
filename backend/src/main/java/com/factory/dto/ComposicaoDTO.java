package com.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComposicaoDTO {
    private Long materiaPrimaId;
    private String materiaPrimaCodigo;
    private String materiaPrimaNome;
    private Double quantidade;
}
