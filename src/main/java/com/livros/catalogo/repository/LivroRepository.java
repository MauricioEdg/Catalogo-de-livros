package com.livros.catalogo.repository;

import com.livros.catalogo.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
    Optional<LivroEntity> findByNomeLivro(String nomeLivro);
    @Query("""
        SELECT DISTINCT l
        FROM LivroEntity l
        LEFT JOIN FETCH l.autores
    """)
    List<LivroEntity> buscarLivrosComAutores();
    @Query("""
    SELECT i
    FROM LivroEntity l
    JOIN l.idioma i
    WHERE l.id = :id
    """)
    List<String> buscarIdiomasPorLivro(@Param("id") Long id);

    @Query("""
    SELECT l
    FROM LivroEntity l
    WHERE :idioma MEMBER OF l.idioma
    """)
    List<LivroEntity> buscarLivrosPorIdioma(@Param("idioma") String idioma);
}
