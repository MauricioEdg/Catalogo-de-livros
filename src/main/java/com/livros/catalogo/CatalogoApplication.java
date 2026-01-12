package com.livros.catalogo;

import com.livros.catalogo.model.ResponseApi;
import com.livros.catalogo.services.Autor;
import com.livros.catalogo.services.DadosLivro;
import com.livros.catalogo.services.ConsumoApi;
import com.livros.catalogo.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://gutendex.com/books/?ids=11");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		ResponseApi dados = conversor.obterDados(json, ResponseApi.class);

		dados.results().forEach(livro ->{
			String nomeLivro = livro.nomeLivro();

			String nomeAutor = livro.autor().stream()
					.findFirst()
					.map(Autor::name)
					.orElse("Autor nao encontrado");

			System.out.println(nomeAutor);
			System.out.println(nomeLivro);
		});


	}
}
