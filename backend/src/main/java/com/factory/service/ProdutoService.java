package com.factory.service;

import com.factory.dto.ComposicaoDTO;
import com.factory.dto.ProdutoDTO;
import com.factory.model.Composicao;
import com.factory.model.Produto;
import com.factory.repository.MateriaPrimaRepository;
import com.factory.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    
    private final ProdutoRepository repository;
    private final MateriaPrimaRepository materiaPrimaRepository;
    
    public List<ProdutoDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public ProdutoDTO findById(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return toDTO(produto);
    }
    
    @Transactional
    public ProdutoDTO create(ProdutoDTO dto) {
        if (repository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Já existe um produto com este código");
        }
        
        Produto produto = new Produto();
        produto.setCodigo(dto.getCodigo());
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());
        
        if (dto.getComposicao() != null) {
            for (ComposicaoDTO compDTO : dto.getComposicao()) {
                Composicao comp = new Composicao();
                comp.setMateriaPrima(materiaPrimaRepository.findById(compDTO.getMateriaPrimaId())
                        .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada")));
                comp.setQuantidade(compDTO.getQuantidade());
                produto.addComposicao(comp);
            }
        }
        
        return toDTO(repository.save(produto));
    }
    
    @Transactional
    public ProdutoDTO update(Long id, ProdutoDTO dto) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        if (!produto.getCodigo().equals(dto.getCodigo()) 
                && repository.existsByCodigo(dto.getCodigo())) {
            throw new RuntimeException("Já existe um produto com este código");
        }
        
        produto.setCodigo(dto.getCodigo());
        produto.setNome(dto.getNome());
        produto.setValor(dto.getValor());
        
        produto.getComposicao().clear();
        
        if (dto.getComposicao() != null) {
            for (ComposicaoDTO compDTO : dto.getComposicao()) {
                Composicao comp = new Composicao();
                comp.setMateriaPrima(materiaPrimaRepository.findById(compDTO.getMateriaPrimaId())
                        .orElseThrow(() -> new RuntimeException("Matéria-prima não encontrada")));
                comp.setQuantidade(compDTO.getQuantidade());
                produto.addComposicao(comp);
            }
        }
        
        return toDTO(repository.save(produto));
    }
    
    @Transactional
    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        repository.deleteById(id);
    }
    
    private ProdutoDTO toDTO(Produto produto) {
        List<ComposicaoDTO> composicao = produto.getComposicao().stream()
                .map(c -> new ComposicaoDTO(
                        c.getMateriaPrima().getId(),
                        c.getMateriaPrima().getCodigo(),
                        c.getMateriaPrima().getNome(),
                        c.getQuantidade()
                ))
                .collect(Collectors.toList());
        
        return new ProdutoDTO(
                produto.getId(),
                produto.getCodigo(),
                produto.getNome(),
                produto.getValor(),
                composicao
        );
    }
}
