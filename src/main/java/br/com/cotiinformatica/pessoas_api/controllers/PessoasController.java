package br.com.cotiinformatica.pessoas_api.controllers;

import br.com.cotiinformatica.pessoas_api.dtos.PessoaRequest;
import br.com.cotiinformatica.pessoas_api.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoasController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody PessoaRequest request) {
        var response = pessoaService.criar(request);
        return ResponseEntity.ok().body(response);
    }
}
