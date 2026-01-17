package br.com.cotiinformatica.pessoas_api;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class PessoasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PessoasApiApplication.class, args);
	}

}
