package br.com.serratec.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PedidoProdutoPK {
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	public PedidoProdutoPK() {
		
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	
	
}
