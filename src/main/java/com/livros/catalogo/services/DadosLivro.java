package com.livros.catalogo.services;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livros.catalogo.services.Autor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title")
        String nomeLivro,
        @JsonAlias("authors")
        List<Autor> autor) {
}
