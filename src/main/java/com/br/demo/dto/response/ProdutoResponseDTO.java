package com.br.demo.dto.response;

import com.br.demo.model.Categoria;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private double preco;
    private String numeroSerie;

    private Categoria categoria;

    public ProdutoResponseDTO(){}

    public ProdutoResponseDTO(Long id, String nome, double preco, String numeroSerie, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.numeroSerie = numeroSerie;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

