package com.factory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materias_primas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrima {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Código é obrigatório")
    @Column(unique = true, nullable = false)
    private String codigo;
    
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @NotNull(message = "Quantidade em estoque é obrigatória")
    @PositiveOrZero(message = "Quantidade deve ser positiva ou zero")
    @Column(nullable = false)
    private Double quantidadeEstoque;
    
    @NotBlank(message = "Unidade de medida é obrigatória")
    @Column(nullable = false)
    private String unidadeMedida;
    
    @PositiveOrZero(message = "Peso deve ser positivo ou zero")
    @Column(nullable = true)
    private Double pesoPorUnidade;

    @PositiveOrZero(message = "Valor deve ser positivo ou zero")
    @Column(nullable = true)
    private Double valorUnidade;
}
