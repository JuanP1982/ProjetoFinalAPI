package br.com.serratec.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

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
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	   @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
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

		Info info = new Info().title("API DE CADASTRO USUÁRIOS").version("1.0").license(licenca)
				.description("API PARA ESTUDOS");

		return new OpenAPI().info(info).servers(List.of(dbServer, webServer));

	}

	
}
