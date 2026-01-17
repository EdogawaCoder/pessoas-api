package br.com.cotiinformatica.pessoas_api.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "mail.enabled", havingValue = "true")
@Component
public class MailComponent {
    
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String bodyString) throws Exception {
       
        //Criando o email
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, "UTF-8");

        //Configurando o email
        helper.setTo(to);
        helper.setFrom("no-reply@edragon.com.br");
        helper.setSubject(subject);
        helper.setText(bodyString, true);

        //Enviando o email
        mailSender.send(message);

    }
}

