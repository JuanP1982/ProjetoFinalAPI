package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.configuration.MailConfig;
import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Endereco;
import br.com.serratec.exception.EmailException;
import br.com.serratec.exception.EnderecoException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private MailConfig mailConfig;

	public List<ClienteResponseDTO> listar() {
		List<Cliente> clientes = repository.findAll();
	
		return clientes.stream().map((c) -> new ClienteResponseDTO(c)).collect(Collectors.toList());
	}

	public ClienteResponseDTO inserir(Cliente cliente) throws Exception {
		if (repository.findByEmail(cliente.getEmail()) != null) {
			throw new EmailException("Email Já Existe na Base");
		}
		Cliente c = new Cliente();
		c.setNome(cliente.getNome());
		c.setEmail(cliente.getEmail());
		c.setCpf(cliente.getCpf());
		c.setTelefone(cliente.getTelefone());
		c.setSenha(encoder.encode(cliente.getSenha()));
		c.setCep(cliente.getCep());
		Endereco endereco = this.getEndereco(cliente.getCep());
		c.setEndereco(endereco);

		repository.save(c);
		mailConfig.sendMail(c.getEmail(), "Cadastro de Usuário no Sistema", c.toString());
		return new ClienteResponseDTO(c);
	}

	public ResponseEntity<String> atualizar(Long id, Cliente cliente) {
		if (repository.existsById(id)) {
			cliente.setId(id);
			cliente.setEndereco(this.getEndereco(cliente.getCep()));
			repository.save(cliente);
			mailConfig.sendMail(cliente.getEmail(), "Seu perfil de Usuário no Sistema foi atualizado", cliente.toString());
			return ResponseEntity.status(HttpStatus.OK).body("Informações Atualizadas com sucesso!");
		}
		throw new ResourceNotFoundException("Usuário com o id: " + id + " não encontrado!");
	}

	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");
		}
		throw new ResourceNotFoundException("Usuário com o id: " + id + " não encontrado!");
	}

	public Endereco getEndereco(String cep) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
				.build();
		HttpResponse<String> response;

		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() != 200) {
				throw new EnderecoException("Erro ao buscar endereço! Código de status: " + response.statusCode());
			}

			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(response.body(), Endereco.class);
		} catch (IOException | InterruptedException e) {
			throw new EnderecoException("Erro ao processar a resposta do servidor: " + e.getMessage(), e);
		}

	}

	public ClienteResponseDTO listarId(Long id) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário com o id: " + id + " não encontrado!"));

		return new ClienteResponseDTO(cliente);
	}
	
	public ClienteResponseDTO autenticar(String email, String senha) {
	    Cliente cliente = repository.findByEmail(email);

	    if (cliente != null && encoder.matches(senha, cliente.getSenha())) {
	        ClienteResponseDTO response = new ClienteResponseDTO(cliente);
	    	return response;
	    } else {
	        return null; 
	    }
	}
}
