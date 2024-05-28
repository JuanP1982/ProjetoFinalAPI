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

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.entity.Pedido;
import br.com.serratec.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@ApiResponses(value = { @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
		@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
		@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
		@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), })
public class PedidoController {

	@Autowired
	private PedidoService service;

	@GetMapping
	@Operation(summary = "Lista todos os pedidos", description = "Listagem de Pedidos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os pedidos"), })
	public List<PedidoResponseDTO> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Lista um pedido", description = "Busca um produto específico pelo ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todo o pedido"), })
	public PedidoResponseDTO listarId(@PathVariable Long id) {
		return service.listarId(id);
	}

	@PostMapping
	@Transactional
	@Operation(summary = "Insere um novo pedido", description = "Inserção de Pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"), })
	public PedidoResponseDTO inserir(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
		return service.inserir(pedidoRequestDTO);
	}

	@PutMapping("/{id}")
	@Transactional
	@Operation(summary = "Atualiza um pedido existente", description = "Atualização de Pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"), })
	public ResponseEntity<PedidoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PedidoRequestDTO pedido) {
        PedidoResponseDTO pedidoAtualizado = service.atualizar(id, pedido);
        if (pedidoAtualizado != null) {
            return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um pedido existente", description = "Exclusão de Pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"), })
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return service.deletar(id);
	}
}
