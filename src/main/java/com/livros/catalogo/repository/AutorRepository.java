package com.livros.catalogo.repository;

import com.livros.catalogo.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNameIgnoreCase(String name);
}
