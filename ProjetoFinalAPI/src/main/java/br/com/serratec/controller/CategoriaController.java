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

import br.com.serratec.entity.Categoria;
import br.com.serratec.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@ApiResponses(value = { 
    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
    @ApiResponse(responseCode = "404", description = "Categoria não encontrado"),
    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), 
})
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping
    @Operation(summary = "Lista todos os categoria", description = "Listagem de Categorias")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna todos os categoria"), 
    })
    public List<Categoria> listar() {
        return service.listar();
    }
       
    @PostMapping
    @Operation(summary = "Insere uma nova categoria", description = "Inserção de Categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
    })
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(service.inserir(categoria), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um categoria existente", description = "Atualização de Categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoria atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrado"),
    })
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaAtualizado = service.atualizar(id, categoria);
        if (categoriaAtualizado != null) {
            return new ResponseEntity<>(categoriaAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um categoria existente", description = "Exclusão de Categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categoria deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Categoria não encontrado"),
    })
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        return service.deletar(id);
        }
    }

