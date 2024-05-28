package br.com.serratec.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ClienteResponseDTO;
import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Carrinho;
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

		for (Long produtoId : pedidoRequestDTO.getProdutoIds()) {
			Produto produto = produtoService.listarId(produtoId);

			carrinhos.add(new Carrinho(pedido, produto));
		}
		pedido.setCarrinhos(carrinhos);
		repository.save(pedido);
		PedidoResponseDTO response = new PedidoResponseDTO(pedido);
		return response;
	}

	// atualizar

	public Pedido atualizar(Long id, @Valid Pedido pedido) {
		if (repository.existsById(id)) {
			pedido.setId(id);
			repository.save(pedido);
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
