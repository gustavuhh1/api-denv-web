package com.br.demo.service;

import com.br.demo.dto.CategoriaDTO;
import com.br.demo.model.Categoria;
import com.br.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaDTO(c.getNome(), c.getDescricao()))
                .collect(Collectors.toList());
    }

    public CategoriaDTO buscarPorId(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaDTO(categoria.getNome(), categoria.getDescricao());
    }

    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO){
        Categoria novaCategoria = new Categoria(categoriaDTO.getNome(), categoriaDTO.getDescricao());
        Categoria categoriaSalva = categoriaRepository.save(novaCategoria);
        return new CategoriaDTO(categoriaSalva.getNome(), categoriaSalva.getDescricao());
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isPresent()){
            Categoria categoria = categoriaOptional.get();
            categoria.setId(categoriaDTO.getId());
            categoria.setNome(categoriaDTO.getNome());
            categoria.setDescricao(categoriaDTO.getDescricao());

            categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria.getNome(), categoria.getDescricao());
        }
        return null;
    }

    public void excluirCategoria(Long id){
        categoriaRepository.deleteById(id);
    }
}