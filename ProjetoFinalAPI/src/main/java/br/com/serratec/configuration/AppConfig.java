package br.com.serratec.configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Endereco;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {
	Endereco endereco = new Endereco();

	@Value("${dominio.openapi.dev-url}")
	String devUrl;
	@Value("${dominio.openapi.prod-url}")
	String webUrl;
	
	@Bean
    WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**").allowedOrigins("http://localhost:5173");
           }
       };
   }
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	OpenAPI myOpenApi() {
		Server dbServer = new Server();
		dbServer.setDescription("URL do server Desenvolvimento");
		dbServer.setUrl(devUrl);

		Server webServer = new Server();
		webServer.setDescription("URL do server Produção");
		webServer.setUrl(webUrl);

		License licenca = new License().name("Apache license").url("https://.....");

		Info info = new Info().title("API E-COMMERCE").version("1.0").license(licenca).description("API PARA ESTUDOS");

		return new OpenAPI().info(info).servers(List.of(dbServer, webServer));

	}
}
