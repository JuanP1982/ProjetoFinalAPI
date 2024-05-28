package br.com.serratec.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.FavoritoDTO;
import br.com.serratec.entity.Favorito;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.FavoritoRepository;
import jakarta.validation.Valid;

@Service
public class FavoritoService {
	@Autowired
	private FavoritoRepository favoritoRepository;

	public FavoritoDTO salvar(Favorito favorito) {
		FavoritoDTO dto = new FavoritoDTO(favorito);
		favoritoRepository.save(favorito);
		return dto;
	}

	public List<FavoritoDTO> listarTodos() {
		List<Favorito> fav = favoritoRepository.findAll();
		return fav.stream().map((f) -> new FavoritoDTO(f)).collect(Collectors.toList());
	}

	public ResponseEntity<String> atualizar(Long id, @Valid Favorito favorito) {
		FavoritoDTO dto = new FavoritoDTO(favorito);
		if (favoritoRepository.existsById(id)) {
			favorito.setId(id);
			favoritoRepository.save(favorito);
			return ResponseEntity.status(HttpStatus.OK).body("Informações Atualizadas com sucesso!");
		}
		throw new ResourceNotFoundException("Usuário com o id: " + id + " não encontrado!");
	}
	
	public ResponseEntity<String> deletar(Long id) {
		if (favoritoRepository.existsById(id)) {
			favoritoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Favorito deletado com sucesso!");
		}
		throw new ResourceNotFoundException("Favorito com o id: " + id + " não encontrado!");
	}	
	
}















