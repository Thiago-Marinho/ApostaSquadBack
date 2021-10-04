package com.treinamento.apostasquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApostasquadApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando conexão com o banco de dados");
		SpringApplication.run(ApostasquadApplication.class, args);
		System.out.println("Conexão com o banco de dados iniciada com sucesso");

	}

}
