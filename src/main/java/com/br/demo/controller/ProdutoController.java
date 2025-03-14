package com.br.demo.controller;


import com.br.demo.dto.request.ProdutoRequestDTO;
import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private  ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){

        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoRequestDTO));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO){
//        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.atualizarProduto(id, produtoRequestDTO));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> excluirProduto(@PathVariable Long id){
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
