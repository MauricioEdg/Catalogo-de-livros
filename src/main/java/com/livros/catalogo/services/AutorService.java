package com.livros.catalogo.services;

import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorEntity salvar(AutorEntity autor) {
        return autorRepository.save(autor);
    }

    public List<AutorEntity> listarTodos() {
        return autorRepository.findAll();
    }

    public Optional<AutorEntity> buscarPorNome(String nome) {
        return autorRepository.findByNameIgnoreCase(nome);
    }
}
