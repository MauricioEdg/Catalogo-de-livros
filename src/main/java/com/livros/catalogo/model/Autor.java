package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autor(
        @JsonAlias("name")
        String name,
        @JsonAlias("birth_year")
        Integer AnoNascimento,
        @JsonAlias("death_year")
        Integer AnoFalecimento,
        @JsonAlias("subjects")
        List<String> livro
) {
}
