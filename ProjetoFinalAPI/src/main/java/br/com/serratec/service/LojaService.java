package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.entity.Cnpj;
import br.com.serratec.entity.Loja;
import br.com.serratec.exception.EnderecoException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.LojaRepository;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repository;
	
	
	public List<Loja> listar() {
		return repository.findAll();
		
	}
	
	public Loja inserir(Loja loja) {
		loja.setCnpjinfo(this.getEndereco(loja));
		return repository.save(loja);
	}
	
	public List<Loja> inserirMuitos(List<Loja> loja) {
		return repository.saveAll(loja);
	}
	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Loja deletado com sucesso!");
		}
		throw new ResourceNotFoundException(" o id: " + id + " não foi encontrado!");
	}
	public Cnpj getEndereco(Loja loja){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://receitaws.com.br/v1/cnpj/"+loja.getCnpj()+""))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new EnderecoException("Erro ao buscar endereço! Código de status: " + response.statusCode());
            }
            
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response.body(), Cnpj.class);
        } catch (IOException | InterruptedException e) {
            throw new EnderecoException("Erro ao processar a resposta do servidor: " + e.getMessage(), e);
        }
	
}
}