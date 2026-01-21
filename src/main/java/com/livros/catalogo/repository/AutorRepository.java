package com.livros.catalogo.repository;

import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.model.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNameIgnoreCase(String name);

    @Query("""
    SELECT i
    FROM AutorEntity a
    JOIN a.livros i
    WHERE a.id = :id
    """)
    List<LivroEntity> buscarLivrosDeAutores(@Param("id") Long id);

    @Query("""
    SELECT a
    FROM AutorEntity a
    WHERE a.anoNascimento <= :ano
      AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)
""")
    List<AutorEntity> buscarAutoresVivosNoAno(@Param("ano") Integer ano);

}
