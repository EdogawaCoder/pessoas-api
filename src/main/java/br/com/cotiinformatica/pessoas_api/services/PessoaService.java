package br.com.cotiinformatica.pessoas_api.services;

import br.com.cotiinformatica.pessoas_api.components.MessageProducer;
import br.com.cotiinformatica.pessoas_api.dtos.PessoaRequest;
import br.com.cotiinformatica.pessoas_api.dtos.PessoaResponse;
import br.com.cotiinformatica.pessoas_api.entities.Pessoa;
import br.com.cotiinformatica.pessoas_api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MessageProducer messageProducer;

    public PessoaResponse criar(PessoaRequest request) {

        //Criando um objeto de Pessoa
        var pessoa = new Pessoa();
        pessoa.setId(UUID.randomUUID().toString());
        pessoa.setNome(request.nome());
        pessoa.setEmail(request.email());

        //Salvar no banco de dados
        pessoaRepository.save(pessoa);

        //Enviar para a mensageria
        messageProducer.send(pessoa);

        //Retornar os dados
        return new PessoaResponse(
                pessoa.getId(), //Id da pessoa
                pessoa.getNome(), //Nome da pessoa
                pessoa.getEmail(), //Email da pessoa
                LocalDateTime.now() //Data e hora atual
        );
    }
}
