package br.com.serratec.dto;

import java.util.Set;

import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.enums.StatusEnum;

public class PedidoRequestDTO {

	private Cliente cliente;
    private StatusEnum status;
    private Set<Long> produtoIds;
	
	public PedidoRequestDTO() {
		
	}
	
	public PedidoRequestDTO(Pedido pedido) {
		status = pedido.getStatus();
		cliente = pedido.getCliente();
	}

	

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Long> getProdutoIds() {
		return produtoIds;
	}

	public void setProdutoIds(Set<Long> produtoIds) {
		this.produtoIds = produtoIds;
	}

	
	
	
	

}
