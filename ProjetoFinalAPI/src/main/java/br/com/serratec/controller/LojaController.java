package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.entity.Loja;
import br.com.serratec.service.LojaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lojas")
@ApiResponses(value = { @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
		@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
		@ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), })

public class LojaController {

	
	@Autowired
	private LojaService service;
	

	@GetMapping
	 @Operation(summary = "Lista todas as lojas criadas", description = "Listagem de Lojas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna todas as Lojas"),})
	 public List<Loja> listar() {
        return service.listar();
    }
	
	
	  @PostMapping
	    @Transactional
	    @Operation(summary = "Insere um novo loja", description = "Inserção de Loja")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "Loja criado com sucesso"),
	    })
	    public ResponseEntity<Loja> inserir(@Valid @RequestBody Loja loja) {
	        Loja novoLoja = service.inserir(loja);
	        return new ResponseEntity<>(novoLoja, HttpStatus.CREATED);
	    }
	  @DeleteMapping("/{id}")
	    @Operation(summary = "Deleta um loja existente", description = "Exclusão de Loja")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "204", description = "Loja deletado com sucesso"),
	        @ApiResponse(responseCode = "404", description = "Loja não encontrado"),
	    })
	    public ResponseEntity<String> deletar(@PathVariable Long id) {
	        return service.deletar(id);
	        }
	    

}
