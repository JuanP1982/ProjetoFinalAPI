package br.com.serratec.service;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import br.com.serratec.entity.FotoProduto;


import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.FotoProdutoRepository;

@Service
public class FotoProdutoService  {
	
	@Autowired 
	private FotoProdutoRepository repository;
	
	public FotoProduto inserir(Produto produto, MultipartFile file) throws IOException {
		FotoProduto foto = new FotoProduto();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		foto.setProduto(produto);
		return repository.save(foto);
	}
	
	public FotoProduto buscar(Long id) {
		Optional<FotoProduto> fotoOptional = repository.findById(id);
		if (!fotoOptional.isPresent()) {
			throw new ResourceNotFoundException("Foto não encontrada");
		}
		Produto Produto = new Produto();
		Produto.setId(id);
		Optional<FotoProduto> foto = repository.findByProduto(Produto);
		if(!foto.isPresent()) {
			throw new ResourceNotFoundException("Foto não encontrada");
		}
		return foto.get();
	}
	
	public ResponseEntity<String> deletar(Long id) {
		Optional<FotoProduto> foto = repository.findById(id);
		if (!foto.isPresent()) {
			throw new ResourceNotFoundException("Foto não encontrada");
		}
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.SC_OK).body("Foto deletada com sucesso!");
	}
	
	public FotoProduto atualizar(Long id, MultipartFile file) throws IOException {
		Optional<FotoProduto> fotoOptional = repository.findById(id);
		if (!fotoOptional.isPresent()) {
			throw new ResourceNotFoundException("Foto não encontrada");
		}
		FotoProduto foto = fotoOptional.get();
		foto.setNome(file.getName());
		foto.setDados(file.getBytes());
		foto.setTipo(file.getContentType());
		return repository.save(foto);
	}
}