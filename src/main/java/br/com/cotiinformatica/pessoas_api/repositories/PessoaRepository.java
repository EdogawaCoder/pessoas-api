package br.com.cotiinformatica.pessoas_api.repositories;

import br.com.cotiinformatica.pessoas_api.entities.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, UUID> {

}
