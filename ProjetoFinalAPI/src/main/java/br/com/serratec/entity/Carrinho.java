package br.com.serratec.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Carrinho {

	@EmbeddedId
	private PedidoProdutoPK id = new PedidoProdutoPK();

	private Double total;

	public Carrinho() {

	}

	public Carrinho(Pedido pedido, Produto produto) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
	}

	public PedidoProdutoPK getId() {
		return id;
	}

	public void setId(PedidoProdutoPK id) {
		this.id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
