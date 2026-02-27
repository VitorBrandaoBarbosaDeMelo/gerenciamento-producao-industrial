package com.factory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "composicoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Composicao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    @JsonIgnore
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "materia_prima_id", nullable = false)
    @NotNull(message = "Matéria-prima é obrigatória")
    private MateriaPrima materiaPrima;
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    @Column(nullable = false)
    private Double quantidade;
}
