package br.com.serratec.controller;

import java.util.List;

//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.serratec.dto.FavoritoRequestDTO;
//import br.com.serratec.dto.FavoritoResponseDTO;
//import br.com.serratec.entity.Favorito;
//import br.com.serratec.service.FavoritoService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.transaction.Transactional;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/favoritos")
//@ApiResponses(value = { 
//    @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
//    @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
//    @ApiResponse(responseCode = "404", description = "Favorito não encontrado"),
//    @ApiResponse(responseCode = "500", description = "Exceção interna da aplicação"), 
//})
//public class FavoritoController {
//
//    @Autowired
//    private FavoritoService service;
//
//    @GetMapping
//    @Operation(summary = "Lista todos os favoritos", description = "Listagem de Favoritos")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Retorna todos os favoritos"), 
//    })
//    public List<FavoritoResponseDTO> listar() {
//        return service.listar();
//    }
//    
//    @GetMapping("/{id}")
//    @Operation(summary = "Lista um favorito", description = "Busca um produto específico pelo ID")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Retorna todo o favorito"), 
//    })
//    public FavoritoResponseDTO listarId(@PathVariable Long id) {
//    	return service.listarId(id);
//    }
//
//    @PostMapping
//    @Transactional
//    @Operation(summary = "Insere um novo favorito", description = "Inserção de Favorito")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "201", description = "Favorito criado com sucesso"),
//    })
//    public FavoritoResponseDTO inserir(@Valid @RequestBody FavoritoRequestDTO favoritoRequestDTO) {
//       return service.inserir(favoritoRequestDTO);
//    }
//
//    @PutMapping("/{id}")
//    @Transactional
//    @Operation(summary = "Atualiza um favorito existente", description = "Atualização de Favorito")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Favorito atualizado com sucesso"),
//        @ApiResponse(responseCode = "404", description = "Favorito não encontrado"),
//    })
//    public ResponseEntity<Favorito> atualizar(@PathVariable Long id, @Valid @RequestBody Favorito favorito) {
//        Favorito favoritoAtualizado = service.atualizar(id, favorito);
//        if (favoritoAtualizado != null) {
//            return new ResponseEntity<>(favoritoAtualizado, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Deleta um favorito existente", description = "Exclusão de Favorito")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "204", description = "Favorito deletado com sucesso"),
//        @ApiResponse(responseCode = "404", description = "Favorito não encontrado"),
//    })
//    public ResponseEntity<String> deletar(@PathVariable Long id) {
//        return service.deletar(id);
//        }
//    }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.FavoritoDTO;
import br.com.serratec.entity.Favorito;
import br.com.serratec.service.FavoritoService;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService favoritoService;

    @PostMapping
    public FavoritoDTO adicionarFavorito(@RequestBody Favorito favorito) {
        return favoritoService.salvar(favorito);
    }

    @GetMapping
    public List<FavoritoDTO> listarFavoritos() {
        return favoritoService.listarTodos();
    }
}