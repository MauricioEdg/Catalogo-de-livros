package com.livros.catalogo.services;

import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.model.LivroEntity;
import com.livros.catalogo.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<AutorEntity> listarTodos() {
        return autorRepository.findAll();
    }

    public List<LivroEntity> buscarLivros(Long id) {
        return autorRepository.buscarLivrosDeAutores(id);
    }

    public List<AutorEntity> buscarAutoresVivosNoAno(int ano) {
        return autorRepository.buscarAutoresVivosNoAno(ano);
    }
}
