package br.com.serratec.dto;

import br.com.serratec.entity.Cliente;

public class ClienteResponseDTO {
	private String nome;
	private String telefone;
	private String email;
	private String cep;
	private String logradouro;

	public ClienteResponseDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.email = cliente.getEmail();
		this.cep = cliente.getCep();
		this.logradouro = cliente.getEndereco().getLogradouro();
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

}
