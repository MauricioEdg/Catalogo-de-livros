package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosLivro(
        @JsonAlias("title")
        String nomeLivro,
        @JsonAlias("authors")
        String nomeAutor) {
}
