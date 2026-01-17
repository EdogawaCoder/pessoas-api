package br.com.cotiinformatica.pessoas_api.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI pessoasApiOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Pessoas API")
                        .description("API para gerenciamento de pessoas")
                        .version("v1")
                        .contact(new Contact()
                                .name("Coti Informática")
                                .email("contato@cotiinformatica.com.br")
                                .url("https://www.cotiinformatica.com.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
