package br.com.serratec.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Carrinho {
	
	@EmbeddedId
	private PedidoProdutoPK id = new PedidoProdutoPK();
	
private Double total;
    
   
    private Integer quantidade;
    private Double precoUnitario;
    
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
    
 

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
