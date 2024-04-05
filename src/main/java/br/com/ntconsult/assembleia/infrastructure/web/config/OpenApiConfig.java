package br.com.ntconsult.assembleia.infrastructure.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("#{systemProperties['openapi.dev-url'] ?: 'http://localhost:8080'}")
    private String devUrl;

    @Value("${openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL do servidor no ambiente de Desenvolvimento");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("URL do servidor no ambiente de Produção");

        Contact contact = new Contact();
        contact.setEmail("wellington31almeida@gmail.com");
        contact.setName("Wellington Almeida");

        License mit = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Votação NT Consult - API")
                .version("1.0")
                .contact(contact)
                .description("Teste Técnico - NT Consult").termsOfService("")
                .license(mit);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
