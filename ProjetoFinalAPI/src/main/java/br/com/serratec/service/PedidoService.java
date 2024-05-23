package br.com.serratec.service;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.serratec.entity.Pedido;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.PedidoRepository;
import jakarta.validation.Valid;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	// listarc
	public List<Pedido> listar() {
		List<Pedido> pedidos = repository.findAll();
		for(Pedido pedido : pedidos) {
			pedido.calculaTotal();
		}
		
		return pedidos;
	}
	
	public Pedido listarId(Long id) {
		Pedido pedido = repository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException("Perfil não encontrado!"));
			pedido.calculaTotal();
			return pedido;
	}
	

	// inserir
	public Pedido inserir(@Valid Pedido pedido) {
		return repository.save(pedido);
	}

	// atualizar

	public Pedido atualizar(Long id, @Valid Pedido pedido) {
		Optional<Pedido> pedidoExistente = repository.findById(id);
		if (pedidoExistente.isPresent()) {
			Pedido pedidoAtualizado = pedidoExistente.get();
			pedidoAtualizado.setCliente(pedido.getCliente());
			pedidoAtualizado.getProdutos().clear();
			pedidoAtualizado.getProdutos().addAll(pedido.getProdutos());
			return repository.save(pedidoAtualizado);
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
