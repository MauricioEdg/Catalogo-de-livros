package com.livros.catalogo.mappers;

import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.model.Livro;
import com.livros.catalogo.model.LivroEntity;

import java.util.List;

public class LivroMapper {

    public static LivroEntity toEntity(Livro livro, List<AutorEntity> autores) {
        LivroEntity entity = new LivroEntity();
        entity.setNomeLivro(livro.nomeLivro());
        entity.setAutores(autores);
        entity.setIdioma(livro.idioma());
        entity.setNumeroDownloads(livro.numeroDownloads());
        return entity;
    }
}
