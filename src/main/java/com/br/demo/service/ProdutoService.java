package com.br.demo.service;

import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Categoria;
import com.br.demo.model.Produto;
import com.br.demo.repository.CategoriaRepository;
import com.br.demo.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<ProdutoResponseDTO> listarProdutos(){

        List<Produto> lista = produtoRepository.findAll();

        return lista.stream()
                .map(p -> new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getNumeroSerie(), p.getCategoria()))
                .collect(Collectors.toList());
    }
    public ProdutoResponseDTO buscarPorId(Long id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(),
                produto.getNumeroSerie(), produto.getCategoria());
    }

    public  ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO){
        Categoria categoria = categoriaRepository.findById(produtoRequestDTO.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        Produto produto = new Produto(produtoRequestDTO.getNome(), produtoRequestDTO.getPreco(),
                produtoRequestDTO.getNumeroSerie(),
                categoria);
        produto = produtoRepository.save(produto);
        return new ProdutoResponseDTO(produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getNumeroSerie(),
                produto.getCategoria());
    }

//    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDTO){
//        Produto produtoExistente = produtoRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
//
//        produtoExistente.setNome(produtoRequestDTO.getNome());
//        produtoExistente.setPreco(produtoRequestDTO.getPreco());
//        produtoExistente.setNumeroSerie(produtoRequestDTO.getNumeroSerie());
//
//        Produto produtoAtualizado = produtoRepository.saveAndFlush(produtoExistente);
//
//        return new ProdutoResponseDTO(produtoAtualizado
//                .getId(),produtoAtualizado.getNome(),
//                produtoAtualizado.getPreco(),
//                produtoAtualizado.getNumeroSerie(),
//                produtoAtualizado.getCategoria());
//    }

    public void excluirProduto(Long id){
        produtoRepository.deleteById(id);
    }

}
