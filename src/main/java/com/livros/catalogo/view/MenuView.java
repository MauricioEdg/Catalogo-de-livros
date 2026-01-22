package com.livros.catalogo.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livros.catalogo.model.AutorEntity;
import com.livros.catalogo.model.LivroEntity;
import com.livros.catalogo.model.ResponseApi;
import com.livros.catalogo.services.AutorService;
import com.livros.catalogo.services.ConsumoApi;
import com.livros.catalogo.services.ConverteDados;
import com.livros.catalogo.services.LivroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuView {

    private final LivroService livroService;
    private final AutorService autorService;

    public MenuView(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }

    public void exibirMenu() throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        ConsumoApi consumoApi = new ConsumoApi();
        ConverteDados conversor = new ConverteDados();

        int opcao;

        do {
            System.out.println("\n=== MENU USUÁRIO ===");
            System.out.println("1 - Buscar livro pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros em um determinado idioma");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();

                    String json = consumoApi.obterDados(
                            "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20")
                    );

                    ResponseApi response = conversor.obterDados(json, ResponseApi.class);

                    response.livroResults().forEach(livro -> {
                        livroService.salvarLivroDaApi(livro);

                        System.out.println("Título: " + livro.nomeLivro());
                        System.out.println("Autor(es):");
                        livro.autor().forEach(a -> System.out.println("- " + a.name()));
                        System.out.println("Idiomas: " + livro.idioma());
                        System.out.println("Downloads: " + livro.numeroDownloads());
                        System.out.println("--------------");
                    });
                    break;

                case 2:
                    System.out.println("======== Mostrando livros salvos =========");
                    List<LivroEntity> livros = livroService.buscarLivros();


                    if (livros.isEmpty()) {
                        System.out.println("Nenhum livro cadastrado.");
                    } else {
                        livros.forEach(l -> {
                            System.out.println("Título: " + l.getNomeLivro());
                            System.out.println("--------------");
                            System.out.println("Autor(es):");
                            l.getAutores().forEach(a ->
                                    System.out.println("- " + a.getName())
                            );
                            System.out.println("--------------");
                            System.out.println("Downloads: " + l.getNumeroDownloads());
                            System.out.println("--------------");

                            List<String> idiomas = livroService.buscarIdiomasPorLivro(l.getId());
                            System.out.println("Idioma(s):");
                            idiomas.forEach(i -> {
                                System.out.println("- " + i);
                            });
                        });
                    }
                    break;
                case 3:
                    System.out.println("======== Mostrando autores salvos =========");
                    System.out.println("--------------");
                    List<AutorEntity> autores = autorService.listarTodos();

                    if (autores.isEmpty()) {
                        System.out.println("Nenhum autor encontrado");
                    } else {
                        autores.forEach(a -> {
                            System.out.println("Nome do autor(a): " + a.getName());
                            System.out.println("----------------");
                            System.out.println("Ano de nascimento: " + a.getAnoNascimento());
                            System.out.println("------------------");
                            System.out.println("Ano de falecimento: " + a.getAnoFalecimento());
                            System.out.println("--------------------");

                            List<LivroEntity> livrosAutores = autorService.buscarLivros(a.getId());
                            System.out.println("Livros: ");
                            livrosAutores.forEach(i -> {
                                System.out.println("- " + i.getNomeLivro());
                            });
                            System.out.println("-----------------------");
                        });
                    }
                case 4:
                    System.out.println("Digite o ano: ");
                    Integer ano = scanner.nextInt();
                    scanner.nextLine();

                    List<AutorEntity> autoresAno = autorService.buscarAutoresVivosNoAno(ano);

                    if (autoresAno.isEmpty()) {
                        System.out.println("Nenhum autor estava vivo nesse ano.");
                    } else {
                        System.out.println("Autores vivos em " + ano + ":");
                        autoresAno.forEach(a ->
                                System.out.println("- " + a.getName())
                        );
                    }
                    break;
                case 5:
                    System.out.println("Digite um idioma para buscar livros: \n" +
                            "es - espanhol \n" +
                            "en - inglês \n" +
                            "fr - francês \n" +
                            "pt - português ");
                    String idioma = scanner.next();
                    List<LivroEntity> idiomas = livroService.buscaLivroIdioma(idioma);

                    if (idiomas.isEmpty()) {
                        System.out.println("Nenhum livro encontrado nesse idioma.");
                    } else {
                        idiomas.forEach(l -> System.out.println("- " + l.getNomeLivro()));
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

