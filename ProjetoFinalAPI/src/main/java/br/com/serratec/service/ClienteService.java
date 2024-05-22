package br.com.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.exception.EmailException;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<ClienteResponseDTO> listar() {
		List<Cliente> clientes = repository.findAll();

		return clientes.stream().map((c) -> new ClienteResponseDTO(c)).collect(Collectors.toList());
	}

	public ClienteResponseDTO inserir(Cliente cliente) {
		if (repository.findByEmail(cliente.getEmail()) != null) {
			throw new EmailException("Email Já Existe na Base");
		}
		Cliente u = new Cliente();
		u.setNome(cliente.getNome());
		u.setEmail(cliente.getEmail());
		u.setCpf(cliente.getCpf());
		u.setTelefone(cliente.getTelefone());
		u.setSenha(encoder.encode(cliente.getSenha()));
		repository.save(u);
		return new ClienteResponseDTO(u);
	}

	public ResponseEntity<String> atualizar(Long id, Cliente cliente) {
		if (repository.existsById(id)) {
			cliente.setId(id);
			repository.save(cliente);
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
}
