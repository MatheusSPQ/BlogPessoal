package com.generation.blogpessoal.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Blog Pessoal")
                        .description("Projeto blog pessoal - Generation Brasil")
                        .version("0.0.1")
                        .license(new License()
                                .name("Generation Brasil")
                                .url("https://brazil.generation.org"))
                        .contact(new Contact()
                                .name("Generation Brasil")
                                .url("https://github.com/conteudoGeneration")
                                .email("conteudoGeneration@generation.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/conteudoGeneration"));

    }

    private ApiResponse createApiResponses(String message) {
        return new ApiResponse().description(message);
    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                ApiResponses apiResponses = operation.getResponses();

                apiResponses.addApiResponse("200", createApiResponses("Successo"));
                apiResponses.addApiResponse("201", createApiResponses("Objeto Persistido!"));
                apiResponses.addApiResponse("204", createApiResponses("Objeto Excluido!"));
            }));
        };
    }

}
