package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="livros")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LivroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeLivro;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<AutorEntity> autores;

    @ElementCollection
    @CollectionTable(
            name = "livro_idiomas",
            joinColumns = @JoinColumn(name = "livro_id")
    )
    @Column(name = "idioma")
    private List<String> idioma;

    private Integer numeroDownloads;


}
