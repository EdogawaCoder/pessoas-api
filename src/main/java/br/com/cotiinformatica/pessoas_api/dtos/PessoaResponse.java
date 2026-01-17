package br.com.cotiinformatica.pessoas_api.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record PessoaResponse(
        String id, //Id da pessoa
        String nome, //Nome da pessoa
        String email, //Email da pessoa
        LocalDateTime dataHoraCriacao //Data e hora de criação
) {
}
