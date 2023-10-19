package com.minsait.curso.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * Representaci&#243;n de la configuraci&#243;n de swagger
 * @author fvelez
 * @version 1.0
 */
@Configuration
public class SwaggerConfig {

	/**
	 * Propiedad para recuperar el nombre del proyecto del archivo de propiedades
	 */
	@Value("${app.project.name}")
    private String projectName;
	
	/**
	 * Propiedad para recuperar la versi&#243;n del proyecto del archivo de propiedades
	 */
	@Value("${app.project.version}")
    private String projectVersion;

	/**
	 * M&#233;todo para recuperar la confguraci&#243;n inicial del Swagger
	 * @return Objeto de clase OpenAPI
	 */
	@Bean
	public OpenAPI springOpenAPI() {
		// Regresa la configuración basica de swagger
		return new OpenAPI()
				.info(new Info().title(projectName)
					.description("API para la biblioteca")
					.version(projectVersion))
				.externalDocs(new ExternalDocumentation()
					.description("springdoc-openapi")
					.url("http://springdoc.org"));
	}
	
}
