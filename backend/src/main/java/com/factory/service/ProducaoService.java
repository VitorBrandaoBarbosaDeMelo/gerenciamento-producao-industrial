package com.factory.service;

import com.factory.dto.SugestaoProducaoDTO;
import com.factory.model.Composicao;
import com.factory.model.MateriaPrima;
import com.factory.model.Produto;
import com.factory.repository.MateriaPrimaRepository;
import com.factory.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProducaoService {
    
    private final ProdutoRepository produtoRepository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    
    /**
     * Calcula a melhor estratégia de produção para maximizar o valor de venda
     * Algoritmo greedy que prioriza produtos com maior retorno financeiro
     */
    public List<SugestaoProducaoDTO> calcularOtimizacao() {
        List<Produto> produtos = produtoRepository.findAll();
        Map<Long, Double> estoqueDisponivel = getEstoqueAtual();
        List<SugestaoProducaoDTO> sugestoes = new ArrayList<>();
        
        // Ordena produtos por valor (maior valor primeiro)
        produtos.sort((p1, p2) -> Double.compare(p2.getValor(), p1.getValor()));
        
        for (Produto produto : produtos) {
            int quantidadeProduzir = calcularQuantidadeMaxima(produto, estoqueDisponivel);
            
            if (quantidadeProduzir > 0) {
                atualizarEstoque(produto, quantidadeProduzir, estoqueDisponivel);
                
                SugestaoProducaoDTO sugestao = new SugestaoProducaoDTO(
                        produto.getId(),
                        produto.getCodigo(),
                        produto.getNome(),
                        produto.getValor(),
                        quantidadeProduzir,
                        produto.getValor() * quantidadeProduzir
                );
                sugestoes.add(sugestao);
            }
        }
        
        return sugestoes;
    }
    
    private Map<Long, Double> getEstoqueAtual() {
        Map<Long, Double> estoque = new HashMap<>();
        List<MateriaPrima> materiasPrimas = materiaPrimaRepository.findAll();
        
        for (MateriaPrima mp : materiasPrimas) {
            estoque.put(mp.getId(), mp.getQuantidadeEstoque());
        }
        
        return estoque;
    }
    
    private int calcularQuantidadeMaxima(Produto produto, Map<Long, Double> estoqueDisponivel) {
        if (produto.getComposicao().isEmpty()) {
            return 0;
        }
        
        int quantidadeMaxima = Integer.MAX_VALUE;
        
        for (Composicao comp : produto.getComposicao()) {
            Long materiaPrimaId = comp.getMateriaPrima().getId();
            Double estoqueAtual = estoqueDisponivel.getOrDefault(materiaPrimaId, 0.0);
            Double quantidadeNecessaria = comp.getQuantidade();
            
            int quantidadePossivel = (int) (estoqueAtual / quantidadeNecessaria);
            quantidadeMaxima = Math.min(quantidadeMaxima, quantidadePossivel);
        }
        
        return quantidadeMaxima == Integer.MAX_VALUE ? 0 : quantidadeMaxima;
    }
    
    private void atualizarEstoque(Produto produto, int quantidade, Map<Long, Double> estoqueDisponivel) {
        for (Composicao comp : produto.getComposicao()) {
            Long materiaPrimaId = comp.getMateriaPrima().getId();
            Double estoqueAtual = estoqueDisponivel.get(materiaPrimaId);
            Double consumo = comp.getQuantidade() * quantidade;
            estoqueDisponivel.put(materiaPrimaId, estoqueAtual - consumo);
        }
    }
}
