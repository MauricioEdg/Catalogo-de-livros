package com.livros.catalogo;

import com.fasterxml.jackson.core.JsonProcessingException;
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
	public void run(String... args){
            try {
                menuView.exibirMenu();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

	}
}
