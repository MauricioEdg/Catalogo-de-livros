package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseApi(
        @JsonAlias("results")
        List<Livro> livroResults

) {
}
