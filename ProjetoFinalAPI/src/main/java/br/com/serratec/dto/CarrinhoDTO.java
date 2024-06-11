package br.com.serratec.dto;

import java.util.Iterator;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Categoria;
import br.com.serratec.enums.CategoriaEnum;

public class CarrinhoDTO {
	private String nomeCliente;
	private String nomeProduto;
	private String url;
	private Integer quantidade;
	private Double valor;
	private Double total = 0.0;
	private Categoria categoria;

	public CarrinhoDTO(Carrinho carrinho) {
		this.nomeCliente = carrinho.getId().getPedido().getCliente().getNome();
		this.nomeProduto = carrinho.getId().getProduto().getNome();
		this.quantidade = carrinho.getQuantidade();
		this.valor = carrinho.getPrecoUnitario();
		this.url = carrinho.getId().getProduto().getUrl();
		this.categoria = carrinho.getId().getProduto().getCategoria();
		this.calculaTotal();
	}

	public void calculaTotal() {
		this.total += this.quantidade * valor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}


	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
