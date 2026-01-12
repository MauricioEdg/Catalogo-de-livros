package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livros.catalogo.services.DadosLivro;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseApi(
        List<DadosLivro> results
) {
}
