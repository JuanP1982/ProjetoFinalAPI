package br.com.serratec.dto;

import java.util.Iterator;

import br.com.serratec.entity.Carrinho;

public class CarrinhoDTO {
	private String nomeCliente;
	private String nomeProduto;
	private Double quantidade;
	private Double valor;
	private Double total=0.0;
	
	public CarrinhoDTO(Carrinho carrinho) {
		this.nomeCliente = carrinho.getId().getPedido().getCliente().getNome();
		this.nomeProduto = carrinho.getId().getProduto().getNome();
		this.quantidade = carrinho.getId().getProduto().getQuantidade();
		this.valor = carrinho.getId().getProduto().getPreco();
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
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
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
	
	
}
