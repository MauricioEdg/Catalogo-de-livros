# 📕 Catálogo de livros
Projeto desenvolvido em Java com Spring Boot, que consome a API pública Gutendex para consulta de livros do Project Gutenberg, permitindo o armazenamento local, filtros avançados e listagens personalizadas através de um menu interativo no terminal.
O sistema foi projetado com foco em boas práticas de arquitetura, persistência com JPA/Hibernate e consultas avançadas em JPQL.
---
# 📝 Funcionalidades
✅ Buscar livros pelo título diretamente na API Gutendex <br>
✅ Persistir livros e autores em banco de dados <br>
✅ Listar livros salvos localmente <br>
✅ Listar livros por idioma <br>
✅ Listar livros de um autor específico <br>
✅ Filtrar autores vivos em um determinado ano <br>
✅ Relacionamento entre Livro ↔ Autor <br>
✅ Tratamento de coleções (List<String>) usando JPQL (MEMBER OF) <br>
✅ Menu interativo via terminal <br>

#  📄 Regras de Negócio
- Um livro pode possuir mais de um idioma
- Um autor pode possuir vários livros
- Autores possuem:
    - Nome
    - Ano de nascimento
    - Ano de falecimento (ou nulo, se vivo)
-Um autor é considerado vivo em determinado ano se:
    - Ano ≥ nascimento
    - Ano ≤ falecimento (ou falecimento nulo)
---
# 🔨 Tecnologias utilizadas
📌 Java <br>
📌 Spring Boot <br>
📌 Hibernate <br>
📌 Spring Data JPA <br>
📌 JPQL <br>
📌 Jackson (JSON) <br>
📌 Mysql <br>
📌 Mysql Workbench 8.0 <br>

---

# 🔗 API externa utilizada
Gutendex API
 https://gutendex.com/

Exemplo de busca por título:

 * GET https?//gutendex.com/books/search=dom%20casmurro
---

# 📚 Estrutura de pastas
src/main/java 
  - com.livros.catalogo 
    - model
       * Entidades JPA 
    - repository
       * Repositórios (JPQL) 
    - service 
        * Regras de negócio 
    - view
        * Menu interativo (console)
    - CatalogoApplication.java
   
---

  # 🔎 Exemplos de consultas JPQL

  ### Livros por idioma
    SELECT l
    FROM LivroEntity l
    WHERE :idioma MEMBER OF l.idioma

  ### Livros de um autor
    SELECT l
    FROM AutorEntity a
    JOIN a.livros l
    WHERE a.id = :id

  ### Autores vivos em um determinado ano
    SELECT a
    FROM AutorEntity a
    WHERE a.anoNascimento <= :ano
    AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)

  # ▶️ Como Executar o Projeto
  ## Pré-requisitos <br>
   - Java 17+ <br>
   - Maven <br>
   - Mysql Workbench 8.0 <br>

  ### Passos
    git clone https://github.com/seu-usuario/catalogo-de-livros.git
    cd catalogo-de-livros
    mvn spring-boot:run

---

#  🔲 Melhorias futuras
 * Interface web (Spring MVC/angular/react)
 * Paginação de resultados
 * Autenticação de usuarios

---

👨‍💻 Autor
Mauricio Rosa <br>
💼 Desenvolvedor Backend Java (Júnior) <br>
📚 Estudante de Tecnologia <br>
🚀 Apaixonado por programação e aprendizado contínuo

# ⭐ Considerações Finais
Este projeto foi desenvolvido com foco em aprendizado prático, integração com API externa e domínio de persistência de dados com JPA, representando uma base sólida para aplicações backend mais robustas. <br> <br>
Se gostou, deixe uma ⭐ no repositório!
