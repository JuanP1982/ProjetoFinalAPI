package br.com.serratec.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	

	// listarc
	public List<PedidoResponseDTO> listar() {
		List<Pedido> pedidos = repository.findAll();
		
		
		 return pedidos.stream().map((p) -> new PedidoResponseDTO(p)).collect(Collectors.toList());
	}
	
	public PedidoResponseDTO listarId(Long id) {
	    Pedido pedido = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
	    PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(pedido);

	    return pedidoResponseDTO;
	}
	

	// inserir
	public PedidoResponseDTO inserir(@Valid PedidoRequestDTO pedidoRequestDTO) {
	    Pedido pedido = new Pedido();
	    
	    pedido.setCliente(pedidoRequestDTO.getCliente());
	    pedido.setStatus(pedidoRequestDTO.getStatus());
	    
	    Set<Carrinho> carrinhos = new HashSet<>();
	    
	    for (int i = 0; i < pedidoRequestDTO.getProdutoIds().size(); i++) {
	        Long produtoId = pedidoRequestDTO.getProdutoIds().get(i);
	        Produto produto = produtoService.listarId(produtoId);
	        
	        Carrinho carrinho = new Carrinho(pedido, produto);
	        carrinho.setPrecoUnitario(pedidoRequestDTO.getPreco().get(i));
	        carrinho.setQuantidade(pedidoRequestDTO.getQuantidade().get(i));
	        
	        carrinhos.add(carrinho);
	    }
	    
	    pedido.setCarrinhos(carrinhos);
	    repository.save(pedido);
	    
	    PedidoResponseDTO response = new PedidoResponseDTO(pedido);
	    return response;
	}

	// atualizar

	public PedidoResponseDTO atualizar(Long id, @Valid PedidoRequestDTO pedido) {
		if (repository.existsById(id)) {
			Pedido pedidoSalvar = repository.findById(id).get();
			pedidoSalvar.setCliente(pedido.getCliente());
			pedidoSalvar.setStatus(pedido.getStatus());
			repository.save(pedidoSalvar);
			return new PedidoResponseDTO(pedidoSalvar);
		}
		throw new ResourceNotFoundException("Pedido com o id: " + id + " não encontrado!");
	}

	// deletar
	public ResponseEntity<String> deletar(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.SC_OK).body("Pedido deletado com sucesso!");
		}
		throw new ResourceNotFoundException("Pedido com o id: " + id + " não encontrado!");
	}
}
