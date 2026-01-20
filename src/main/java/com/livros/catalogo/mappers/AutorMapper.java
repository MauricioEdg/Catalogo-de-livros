package com.livros.catalogo.mappers;

import com.livros.catalogo.model.Autor;
import com.livros.catalogo.model.AutorEntity;

public class AutorMapper {

    public static AutorEntity toEntity(Autor autor) {
        AutorEntity entity = new AutorEntity();
        entity.setName(autor.name());
        entity.setAnoNascimento(autor.anoNascimento());
        entity.setAnoFalecimento(autor.anoFalecimento());
        return entity;
    }
}
