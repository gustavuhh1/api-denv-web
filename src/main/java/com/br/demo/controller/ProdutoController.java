package com.br.demo.controller;


import com.br.demo.dto.response.ProdutoResponseDTO;
import com.br.demo.model.Produto;
import com.br.demo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    public ResponseEntity<List<ProdutoResponseDTO>>
}
