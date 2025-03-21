package com.br.demo.controller;


import com.br.demo.dto.CategoriaDTO;
import com.br.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private  CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarProdutos(){

        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criarProduto(@RequestBody CategoriaDTO categoriaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoriaRequestDTO));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CategoriaResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody CategoriaResponseDTO categoriaResponseDTO){
//        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.atualizarCategoria(id, categoriaResponseDTO));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> excluirProduto(@PathVariable Long id){
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
