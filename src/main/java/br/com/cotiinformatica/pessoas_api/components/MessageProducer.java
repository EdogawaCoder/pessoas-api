package br.com.cotiinformatica.pessoas_api.components;

import br.com.cotiinformatica.pessoas_api.entities.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Queue queue;

    /*
        Método para gravar os dados de uma pessoa na fila
     */
    public void send(Pessoa pessoa) {
        try {
            //Converter os dados da pessoa em JSON
            var json = objectMapper.writeValueAsString(pessoa);
            //Gravar na fila
            rabbitTemplate.convertAndSend(queue.getName(), json);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
