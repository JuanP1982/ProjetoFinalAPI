package br.com.serratec.dto;

import java.util.List;
import java.util.Set;

import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Pedido;
import br.com.serratec.entity.Produto;
import br.com.serratec.enums.StatusEnum;

public class PedidoRequestDTO {

	private Cliente cliente;
    private StatusEnum status;
    private List<Long> produtoIds;
    private String produtoNome;
    private List<Double> preco;
    private List<Integer> quantidade;
    
    
    


	
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

	

	public String getProdutoNome() {
		return produtoNome;
	}

	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}

	public List<Long> getProdutoIds() {
		return produtoIds;
	}

	public void setProdutoIds(List<Long> produtoIds) {
		this.produtoIds = produtoIds;
	}

	public List<Double> getPreco() {
		return preco;
	}

	public void setPreco(List<Double> preco) {
		this.preco = preco;
	}

	public List<Integer> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}

	

	

	
	
	
	

}
