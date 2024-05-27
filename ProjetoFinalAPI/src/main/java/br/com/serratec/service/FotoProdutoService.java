package br.com.serratec.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		Produto Produto = new Produto();
		Produto.setId(id);
		Optional<FotoProduto> foto = repository.findByProduto(Produto);
		if(!foto.isPresent()) {
			throw new ResourceNotFoundException("Foto não encontrada");
		}
		return foto.get();
	}
}
