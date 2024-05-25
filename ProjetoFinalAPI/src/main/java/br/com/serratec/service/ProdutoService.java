package br.com.serratec.service;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	// listarc
	public List<Produto> listar() {
		return repository.findAll();
	}

	// inserir
	public Produto inserir(@Valid Produto produto) {
		
		return repository.save(produto);
	}
	
	public Produto listarId(Long id) {
		Produto produto = repository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Perfil não encontrado!"));
//			pedido.calculaTotal();
			return produto;
	}
	
	//inserirVarios
	
	public List<Produto> inserirMuitos(List<Produto> produto) {
		return repository.saveAll(produto);
	}

	// atualizar
	public Produto atualizar(Long id, @Valid Produto produto) {
		Optional<Produto> produtoExistente = repository.findById(id);
		if (produtoExistente.isPresent()) {
			Produto produtoAtualizado = produtoExistente.get();
			produtoAtualizado.setId(id);
			return repository.save(produtoAtualizado);
		}
		throw new ResourceNotFoundException("Produto com o id: " + id + " não encontrado!");
	}

	// deletar
	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Produto deletado com sucesso!");
		}
		throw new ResourceNotFoundException("Produto com o id: " + id + " não encontrado!");
	}
}
