package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Livro(
        @JsonAlias("title")
        String nomeLivro,
        @JsonAlias("authors")
        List<Autor> autor,
        @JsonAlias("languages")
        List<String> idioma,
        @JsonAlias("download_count")
        Integer numeroDownloads) {
}
