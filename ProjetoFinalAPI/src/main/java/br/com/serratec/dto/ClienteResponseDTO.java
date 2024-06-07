package br.com.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.Cliente;
import br.com.serratec.entity.Pedido;

public class ClienteResponseDTO {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	private String cep;
	private String logradouro;
	private Set<PedidoResponseDTO> pedido = new HashSet<>();

	public ClienteResponseDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cep = cliente.getCep();
		this.logradouro = cliente.getEndereco().getLogradouro();
		this.id = cliente.getId();
		for(Pedido pedido: cliente.getPedidos()) {
			PedidoResponseDTO processado = new PedidoResponseDTO(pedido);
			this.pedido.add(processado);
		}
	}

	public ClienteResponseDTO() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Set<PedidoResponseDTO> getPedido() {
		return pedido;
	}

	public void setPedido(Set<PedidoResponseDTO> pedido) {
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
