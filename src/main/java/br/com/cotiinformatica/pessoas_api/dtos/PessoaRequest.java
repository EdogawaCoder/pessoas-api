package br.com.cotiinformatica.pessoas_api.dtos;

public record PessoaRequest(
        String nome,    //nome da pessoa
        String email    //email da pessoa
) {
}
