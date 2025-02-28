package com.br.demo.dto.response;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private double preco;
    private String numeroSerie;

    public ProdutoResponseDTO(){}

    public ProdutoResponseDTO(Long id, String nome, double preco, String numeroSerie) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.numeroSerie = numeroSerie;
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
}

