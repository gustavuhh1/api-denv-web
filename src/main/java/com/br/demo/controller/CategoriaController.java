package com.br.demo.controller;


import com.br.demo.dto.request.CategoriaRequestDTO;
import com.br.demo.dto.response.CategoriaResponseDTO;
import com.br.demo.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarProdutos(){

        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarProduto(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoriaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody CategoriaResponseDTO categoriaResponseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.atualizarCategoria(id, categoriaResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> excluirProduto(@PathVariable Long id){
        categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
