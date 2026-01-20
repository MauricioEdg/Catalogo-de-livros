package com.livros.catalogo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.livros.catalogo.model.ResponseApi;
import com.livros.catalogo.services.ConsumoApi;
import com.livros.catalogo.services.ConverteDados;
import com.livros.catalogo.view.MenuView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@Service
@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {
	private final MenuView menuView;
	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}

	public CatalogoApplication(MenuView menuView) {
		this.menuView = menuView;
	}
	@Override
	public void run(String... args) throws Exception {

		/*ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://gutendex.com/books/?ids=11");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		ResponseApi dados = conversor.obterDados(json, ResponseApi.class);
		dados.livroResults().forEach(livro ->{
			System.out.println("livro: " + livro.nomeLivro());
			System.out.println("nascimento: " + livro.numeroDownloads());
			System.out.println("idiomas: " + livro.idioma());
			livro.autor().forEach( autor ->{
						System.out.println("autor: " + autor.name());
						System.out.println("nascimento: " + autor.anoNascimento());
						System.out.println("falecimento: " + autor.anoFalecimento());
					}
					);

        });*/
        System.out.println("------------------------------------------------------------------------");
            try {
                menuView.exibirMenu();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }




		/*
		Menu usuário
		1 - buscar livro pelo titulo (unica opção que busca diretamente na api)
			livro[
				titulo
				autor
				idioma
				numero de downloads
				]
			autor[ (buscado junto na opção 1, mas nao mostrado, apenas salvo no bd)
				nome
				ano de nascimento
				ano de falecimento
				livros[]
				]

		2 - listar livros registrados
			livro[
				titulo
				autor
				idioma
				numero de downloads
				]

		3 - listar autores registrados
			autor[
				nome
				ano de nascimento
				ano de falecimento
				livros[]
				]

		4 - listar autores vivos em um determinado ano
			autor[
				nome
				ano de nascimento
				ano de falecimento
				livros[]
				]

		5 - listar livros em um determinado idioma
			es - espanhol
			en - inglês
			fr - francês
			pt - português

		0 - Sair



		 */


	}
}
