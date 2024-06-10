package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.serratec.entity.Pedido;
import br.com.serratec.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@ApiResponses(value = { @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
		@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), })
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	@Operation(summary = "Lista todos os clientes", description = "Listagem de Clientes")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os clientes"), })
	public List<ClienteResponseDTO> listar() {
		return service.listar();
	}

	@PostMapping
	@Operation(summary = "Insere um novo cliente", description = "Inserção de Cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"), })

	public ResponseEntity<ClienteResponseDTO> inserir(@Valid @RequestBody Cliente cliente) throws Exception {
		ClienteResponseDTO novoCliente = service.inserir(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	// teste branch
	@PutMapping("{id}")
	@Operation(summary = "Atualiza um cliente existente", description = "Atualização de Cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"), })
	public ResponseEntity<String> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
		return service.atualizar(id, cliente);
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deleta um cliente existente", description = "Exclusão de Cliente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso"), })
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return service.deletar(id);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista todos os clientes", description = "Listagem de Clientes")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os clientes"), })
	public ClienteResponseDTO listarId(@PathVariable Long id) {
		return service.listarId(id);
	}
	
	@PostMapping("/login")
    @Operation(summary = "Autentica um cliente", description = "Autenticação de Cliente")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Cliente autenticado com sucesso"), 
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas") 
    })
	
    public ResponseEntity<ClienteResponseDTO> autenticar(@RequestBody Cliente credenciais) {
		ClienteResponseDTO cliente = service.autenticar(credenciais.getEmail(), credenciais.getSenha());
        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
