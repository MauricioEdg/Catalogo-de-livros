package com.livros.catalogo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="autores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer anoNascimento;
    private Integer anoFalecimento;

    @ManyToMany(mappedBy = "autores")
    private List<LivroEntity> livros;

}
