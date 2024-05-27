package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.exception.FuncionarioNaoPodeSerDeletadoException;
import br.com.serratec.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioResponseDTO> findAll() {
        return funcionarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> findById(@PathVariable Long id) {
        Optional<FuncionarioResponseDTO> funcionario = funcionarioService.findById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Funcionario save(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        if (!funcionarioService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        funcionario.setId(id);
        return ResponseEntity.ok(funcionarioService.save(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.ok("Funcionário deletado com sucesso.");
        } catch (FuncionarioNaoPodeSerDeletadoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}