package br.com.cotiinformatica.pessoas_api.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pessoas")
public class Pessoa {

    @Id
    private String id;
    private String nome;
    private String email;
}
