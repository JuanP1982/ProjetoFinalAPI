package br.com.serratec.dto;

import java.util.Iterator;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.enums.CategoriaEnum;

public class CarrinhoDTO {
	private String nomeCliente;
	private String nomeProduto;
	private Double quantidade;
	private Double valor;
	private Double total = 0.0;
	private CategoriaEnum categoria;
	private Double totalConvertido=0.0;

	public CarrinhoDTO(Carrinho carrinho) {
		this.nomeCliente = carrinho.getId().getPedido().getCliente().getNome();
		this.nomeProduto = carrinho.getId().getProduto().getNome();
		this.quantidade = carrinho.getId().getProduto().getQuantidade();
		this.valor = carrinho.getId().getProduto().getPreco();
		this.categoria = carrinho.getId().getProduto().getCategoria();
		this.calculaTotal();
		this.calculaConvertido(carrinho);
		
	}

	public void calculaTotal() {
		this.total += this.quantidade * valor;
	}
	public void calculaConvertido(Carrinho carrinho){
		this.totalConvertido=total*carrinho.getId().getPedido().getTaxa();
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

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public Double getTotalConvertido() {
		return totalConvertido;
	}

	public void setTotalConvertido(Double totalConvertido) {
		this.totalConvertido = totalConvertido;
	}
	

}
