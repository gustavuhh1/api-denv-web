package com.br.demo.service;


import com.br.demo.dto.request.CategoriaRequestDTO;
import com.br.demo.dto.response.CategoriaResponseDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private  CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDTO> listarCategorias(){

        List<Categoria> lista = categoriaRepository.findAll();

        return lista.stream()
                .map(c -> new CategoriaResponseDTO(c.getId(), c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }
    public CategoriaResponseDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Categoria não encontrado"));
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    public  CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaRequestDTO){
        Categoria novaCategoria = new Categoria(
                categoriaRequestDTO.getNome(),
                categoriaRequestDTO.getDescricao());
        Categoria categoriaSalvo = categoriaRepository.save(novaCategoria);
        return new CategoriaResponseDTO(categoriaSalvo.getId(),categoriaRequestDTO.getNome(),categoriaRequestDTO.getDescricao());
    }

//    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaResponseDTO categoriaResponseDTO){
//        Categoria categoriaExistente = categoriaRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Categoria não encontrado"));
//
//        categoriaExistente.setNome(categoriaResponseDTO.getNome());
//        categoriaExistente.setDescricao(categoriaResponseDTO.getDescricao());
//
//        Categoria categoriaAtualizada = categoriaRepository.saveAndFlush(categoriaExistente);
//
//        return new CategoriaResponseDTO(categoriaAtualizada
//                .getId(),categoriaAtualizada.getNome(),
//                categoriaAtualizada.getDescricao());
//    }

    public void excluirCategoria(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new EntityNotFoundException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }

}
