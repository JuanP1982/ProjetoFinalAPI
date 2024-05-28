package br.com.serratec.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Pedido;

public class PedidoResponseDTO {

	private String status;
	private LocalDate dataPedido;
	private Set<CarrinhoDTO> carrinho = new HashSet<>();
	private Double totalCarrinho = 0.0;
	private Double totalConvertido = 0.0;
	

	public PedidoResponseDTO(Pedido pedido) {
		this.status = pedido.getStatus();
		this.dataPedido = pedido.getDataPedido();
		for (Carrinho carrinho : pedido.getCarrinhos()) {
			CarrinhoDTO processado = new CarrinhoDTO(carrinho);
			this.totalCarrinho += processado.getTotal();
			this.carrinho.add(processado);
		}
		this.totalConvertido=totalCarrinho/pedido.getTaxa();
		
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Set<CarrinhoDTO> getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Set<CarrinhoDTO> carrinho) {
		this.carrinho = carrinho;
	}

	public Double getTotalCarrinho() {
		return totalCarrinho;
	}

	public void setTotalCarrinho(Double totalCarrinho) {
		this.totalCarrinho = totalCarrinho;
	}

	public Double getTotalConvertido() {
		return totalConvertido;
	}

	public void setTotalConvertido(Double totalConvertido) {
		this.totalConvertido = totalConvertido;
	}
	


	}

