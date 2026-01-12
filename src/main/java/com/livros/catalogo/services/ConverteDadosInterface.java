package com.livros.catalogo.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ConverteDadosInterface {

    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
