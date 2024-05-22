package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public List<ClienteResponseDTO> listar() {
		return service.listar();
	}

	@PostMapping
	public ClienteResponseDTO inserir(@RequestBody Cliente cliente) {
		return service.inserir(cliente);
	}

	@PutMapping("{id}")
	public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		return service.atualizar(id, cliente);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return service.deletar(id);
	}

}
