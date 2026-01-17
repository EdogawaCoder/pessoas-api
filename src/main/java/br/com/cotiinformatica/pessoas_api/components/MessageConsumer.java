package br.com.cotiinformatica.pessoas_api.components;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.cotiinformatica.pessoas_api.entities.Pessoa;

@Component
public class MessageConsumer {

    @Autowired
    private MailComponent mailComponent;

    @Autowired
    private ObjectMapper objectMapper;


    @RabbitListener(queues = "pessoas-api")
    public void receive(@Payload String payload) {
        try {

            var pessoa = objectMapper.readValue(payload, Pessoa.class);

            var to = pessoa.getEmail();
            var subject = "Bem-vindo ao nosso sistema Edragorn";
            var bodyString = """
                    <html>
                        <body style="font-family: Arial, Helvetica, sans-serif; background-color: #f4f6f8; padding: 20px;">
                            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 6px;">
                                
                                <h2 style="color: #2c3e50;">Cadastro realizado com sucesso!</h2>
                                
                                <p>Olá <strong>%s</strong>,</p>
                                
                                <p>
                                    Seu cadastro foi realizado com sucesso em nosso sistema.
                                    Abaixo seguem os dados registrados:
                                </p>
                                
                                <table style="width: 100%%; border-collapse: collapse; margin-top: 15px;">
                                    <tr>
                                        <td style="padding: 8px; border: 1px solid #ddd;"><strong>Nome</strong></td>
                                        <td style="padding: 8px; border: 1px solid #ddd;">%s</td>
                                    </tr>
                                    <tr>
                                        <td style="padding: 8px; border: 1px solid #ddd;"><strong>Email</strong></td>
                                        <td style="padding: 8px; border: 1px solid #ddd;">%s</td>
                                    </tr>
                                </table>
                                
                                <p style="margin-top: 20px;">
                                    Caso você não tenha realizado este cadastro, por favor entre em contato conosco.
                                </p>
                                
                                <hr style="margin: 20px 0;" />
                                
                                <p style="font-size: 12px; color: #7f8c8d;">
                                    © %d - Pessoas API<br/>
                                    Este é um e-mail automático, por favor não responda.
                                </p>
                            </div>
                        </body>
                    </html>
                """.formatted(
                    pessoa.getNome(),
                    pessoa.getNome(),
                    pessoa.getEmail(),
                    java.time.Year.now().getValue()
                );

            mailComponent.sendEmail(to, subject, bodyString);

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
