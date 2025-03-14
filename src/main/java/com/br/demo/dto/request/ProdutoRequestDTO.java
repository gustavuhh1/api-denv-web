package com.br.demo.dto.request;

public class ProdutoRequestDTO {
    private String nome;
    private double preco;
    private String numeroSerie;

    private Long categoriaId;

    public ProdutoRequestDTO(String nome, double preco, String numeroSerie, Long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.numeroSerie = numeroSerie;
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}
