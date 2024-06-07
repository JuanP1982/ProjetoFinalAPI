package br.com.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.validation.Valid;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private FotoProdutoService fotoService;

	// listarc
	public List<Produto> listar() {
		return repository.findAll().stream().map((p) -> linkImagem(p)).collect(Collectors.toList());
	}

	// inserir
	public Produto inserir(@Valid Produto produto, MultipartFile file) throws IOException {
		produto = repository.save(produto);
		fotoService.inserir(produto, file);
		
		return linkImagem(produto);
	}

	public Produto listarId(Long id) {
		Optional<Produto> produto = repository.findById(id);
		return linkImagem(produto.get());
		
	}

	// inserirVarios

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
	
	public Produto linkImagem(Produto produto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produto/{id}/foto")
				.buildAndExpand(produto.getId()).toUri();
		
		produto.setUrl(uri.toString());
		return produto;
	}
}