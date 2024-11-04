package desafioapirest.dio.doc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerDoc {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Organização Financeira")
                        .version("1.0")
                        .description("Este projeto de organização financeira ajuda no controle de receitas e despesas para uma casa ou empresa, "
                                + "permitindo registrar todas as transações e gerando relatórios mensais detalhados. As categorias auxiliam "
                                + "na identificação de onde se gasta mais e com o quê. O projeto foi desenvolvido em Java, utilizando Spring Boot, "
                                + "e o banco de dados PostgreSQL.")
                        .contact(new Contact()
                                .name("Raytsson")
                                .url("https://github.com/Raytsson")
                                .email("raytssonmoraes@hotmail.com"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub - Raytsson")
                        .url("https://github.com/Raytsson"));
    }
}
