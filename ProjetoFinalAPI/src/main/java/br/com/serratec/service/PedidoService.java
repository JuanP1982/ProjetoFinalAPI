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

	@Autowired
	private TaxaCambioService taxaCambioService;

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

	

		// Salvando o pedido
		

		// Consultando a taxa de câmbio
		var responseDTO = taxaCambioService.consultarMoeda(pedidoRequestDTO.getMoeda());
		if (responseDTO == null || responseDTO.getCotacoes() == null) {
			throw new ResourceNotFoundException(
					"Taxa de câmbio não encontrada para a moeda especificada: " + pedidoRequestDTO.getMoeda());
		}

		// Corrigindo a chave para buscar a cotação correta
		String quoteKey = "USD" + pedidoRequestDTO.getMoeda();
		Double taxaConversao = responseDTO.getCotacoes().get(quoteKey);
		System.out.println(taxaConversao);
		if (taxaConversao == null) {
			throw new ResourceNotFoundException(
					"Taxa de conversão não encontrada para a moeda especificada: " + pedidoRequestDTO.getMoeda());
		}

		
		pedido.setCliente(pedidoRequestDTO.getCliente());
		pedido.setStatus(pedidoRequestDTO.getStatus());
		pedido.setMoeda(pedidoRequestDTO.getMoeda());
		pedido.setTaxa(taxaConversao);
				
		Set<Carrinho> carrinhos = new HashSet<>();
		for (Long produtoId : pedidoRequestDTO.getProdutoIds()) {
			Produto produto = produtoService.listarId(produtoId);
			carrinhos.add(new Carrinho(pedido, produto));
		}
		pedido.setCarrinhos(carrinhos);
		repository.save(pedido);

		// Criando o PedidoResponseDTO
		PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(pedido);
		
		return pedidoResponseDTO;
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
