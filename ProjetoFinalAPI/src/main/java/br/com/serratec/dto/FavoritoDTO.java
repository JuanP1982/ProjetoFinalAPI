package br.com.serratec.dto;

import java.util.Iterator;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Favorito;
import br.com.serratec.enums.CategoriaEnum;

public class FavoritoDTO {
	private String nomeCliente;
	private String nomeProduto;
	private CategoriaEnum categoria;

	public FavoritoDTO(Favorito favorito) {
		this.nomeCliente = favorito.getCliente().getNome();
		this.nomeProduto = favorito.getProduto().getNome();
		this.categoria = favorito.getProduto().getCategoria(); 
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

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

}
