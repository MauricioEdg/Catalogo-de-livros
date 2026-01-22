package com.livros.catalogo.services;

import com.livros.catalogo.mappers.AutorMapper;
import com.livros.catalogo.mappers.LivroMapper;
import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.model.Livro;
import com.livros.catalogo.model.LivroEntity;
import com.livros.catalogo.repository.AutorRepository;
import com.livros.catalogo.repository.LivroRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Transactional
    public void salvarLivroDaApi(Livro livroApi) {
        // Evita salvar livro duplicado
        if (livroRepository.findByNomeLivro(livroApi.nomeLivro()).isPresent()) {
            System.out.println("Livro já cadastrado.");
            return;
        }
        List<AutorEntity> autores = livroApi.autor().stream()
                .map(autorApi ->
                        autorRepository.findByNameIgnoreCase(autorApi.name())
                                .orElseGet(() ->
                                        autorRepository.save(
                                                AutorMapper.toEntity(autorApi)
                                        )
                                )
                )
                .toList();

        LivroEntity livroEntity = LivroMapper.toEntity(livroApi, autores);

        livroRepository.save(livroEntity);

        System.out.println("Livro e autores salvos com sucesso!");
    }


    @Transactional(readOnly = true)
    public List<LivroEntity> buscarLivros() {
        return livroRepository.buscarLivrosComAutores();
    }
    public List<String> buscarIdiomasPorLivro(Long id) {
        return livroRepository.buscarIdiomasPorLivro(id);
    }
    public List<LivroEntity> buscaLivroIdioma(String idioma){
        return livroRepository.buscarLivrosPorIdioma(idioma);
    }


}

