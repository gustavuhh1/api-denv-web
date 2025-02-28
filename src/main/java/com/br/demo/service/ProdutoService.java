package com.br.demo.service;

import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Produto;
import com.br.demo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarProdutos(){

        List<Produto> lista = produtoRepository.findall();

        return lista.stream()
                .map(p -> new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getNumeroSerie()))
                .collect(Collectors.toList());
    }
    public ProdutoResponseDTO buscarPorId(Long id){
        Produto produto = produtoRepository.finById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(),
                produto.getNumeroSerie());
    }

    public  ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO){
        Produto novoProduto = new Produto(null,
                produtoRequestDTO.getNome(),
                produtoRequestDTO.getPreco(),
                produtoRequestDTO.getNumeroSerie());
        Produto produtoSalvo = produtoRepository.save(novoProduto);
        return new ProdutoResponseDTO(produtoSalvo.getId(),produtoRequestDTO.getNome(),produtoRequestDTO.getPreco()
        ,produtoRequestDTO.getNumeroSerie());
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDTO){
        Produto produtoExistente = produtoRepository.finById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setNome(produtoRequestDTO.getNome());
        produtoExistente.setPreco(produtoRequestDTO.getPreco());
        produtoExistente.setNumeroSerie(produtoRequestDTO.getNumeroSerie());

        Produto produtoAtualizado = produtoRepository.update(produtoExistente);

        return new ProdutoResponseDTO(produtoAtualizado
                .getId(),produtoAtualizado.getNome(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getNumeroSerie());
    }

    public void excluirProduto(Long id){
        produtoRepository.deleteById(id);
    }

}
