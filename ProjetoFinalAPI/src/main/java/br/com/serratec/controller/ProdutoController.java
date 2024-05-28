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

import br.com.serratec.entity.Produto;
import br.com.serratec.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
@ApiResponses(value = { @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
		@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
		@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
		@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), })
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping
	@Operation(summary = "Lista todos os produto", description = "Listagem de Produtos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna todos os produto"), })
	public List<Produto> listar() {
		return service.listar();
	}

	@PostMapping
	@Operation(summary = "Insere um novo produto", description = "Inserção de Produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"), })
	public ResponseEntity<List<Produto>> inserir(@Valid @RequestBody List<Produto> pedido) {
		List<Produto> novoProduto = service.inserirMuitos(pedido);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualiza um produto existente", description = "Atualização de Produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado"), })
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto pedido) {
		Produto pedidoAtualizado = service.atualizar(id, pedido);
		if (pedidoAtualizado != null) {
			return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um produto existente", description = "Exclusão de Produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado"), })
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		return service.deletar(id);
	}
}
