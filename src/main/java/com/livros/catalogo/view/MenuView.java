package com.livros.catalogo.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livros.catalogo.model.LivroEntity;
import com.livros.catalogo.model.ResponseApi;
import com.livros.catalogo.services.ConsumoApi;
import com.livros.catalogo.services.ConverteDados;
import com.livros.catalogo.services.LivroService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuView {

    private final LivroService livroService;

    public MenuView(LivroService livroService) {
        this.livroService = livroService;
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

