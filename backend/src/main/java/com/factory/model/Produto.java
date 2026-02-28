package com.factory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Código é obrigatório")
    @Column(unique = true, nullable = false)
    private String codigo;
    
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    @Column(nullable = false)
    private Double valor;

    @NotNull(message = "Margem de lucro é obrigatória")
    @PositiveOrZero(message = "Margem deve ser positiva ou zero")
    @Column(nullable = false)
    private Double margemLucro = 50.0;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composicao> composicao = new ArrayList<>();
    
    public void addComposicao(Composicao comp) {
        composicao.add(comp);
        comp.setProduto(this);
    }
    
    public void removeComposicao(Composicao comp) {
        composicao.remove(comp);
        comp.setProduto(null);
    }

    public Double calcularCustoTotal() {
        return composicao.stream()
                .mapToDouble(comp -> comp.getMateriaPrima().getValorUnidade() != null 
                    ? comp.getMateriaPrima().getValorUnidade() * comp.getQuantidade() 
                    : 0.0)
                .sum();
    }

    public Double calcularValorComMargem() {
        Double custoTotal = calcularCustoTotal();
        Double margem = (margemLucro != null ? margemLucro : 0.0) / 100.0;
        return custoTotal * (1.0 + margem);
    }

    public void atualizarValorAutomatico() {
        this.valor = calcularValorComMargem();
    }
}
