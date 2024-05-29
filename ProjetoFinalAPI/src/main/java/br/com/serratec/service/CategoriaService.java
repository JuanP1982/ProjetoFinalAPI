package br.com.serratec.service;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Categoria;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.CategoriaRepository;
import jakarta.validation.Valid;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	// listarc
	public List<Categoria> listar() {
		List<Categoria> categorias = repository.findAll();

		return categorias;
	}

	public Categoria listarId(Long id) {
		Categoria categoria = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrado!"));

		return categoria;
	}

	// inserir
	public Categoria inserir(@Valid Categoria categoria) {
		return repository.save(categoria);
	}

	// atualizar

	public Categoria atualizar(Long id, @Valid Categoria categoria) {
		if (repository.existsById(id)) {
			Categoria categoriaSalvar = repository.findById(id).get();
			categoriaSalvar.setDescricao(categoria.getDescricao());
			categoriaSalvar.setId(id);
			categoriaSalvar.setProdutos(categoria.getProdutos());
			categoriaSalvar.setNome(categoria.getNome());
			repository.save(categoriaSalvar);
			return categoriaSalvar;
		}
		throw new ResourceNotFoundException("Categoria com o id: " + id + " não encontrado!");
	}

	// deletar
	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Categoria deletado com sucesso!");
		}
		throw new ResourceNotFoundException("Categoria com o id: " + id + " não encontrado!");
	}
}
