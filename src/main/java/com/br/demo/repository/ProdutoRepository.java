package com.br.demo.repository;

import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Produto;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    private final List<Produto> produtos = new ArrayList<>();
    private Long nextId = 1L;

    public List<Produto> findall(){
        return produtos;
    }
    public Optional<Produto> finById(Long id){
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
    public Produto save(@NotNull Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }

    public void deleteById(Long id){
        produtos.removeIf(p -> p.getId().equals(id));
    }

    public Produto update(Produto produto){
        return produtos.stream()
                .filter(p -> p.getId().equals(produto.getId()))
                .findFirst()
                .map(p -> {
                    p.setNome(produto.getNome());
                    p.setPreco(produto.getPreco());
                    p.setNumeroSerie(produto.getNumeroSerie());
                    return p;
                })
                .orElseThrow(() -> new RuntimeException("Produto não encontrado para atualizações"));
    }



}
