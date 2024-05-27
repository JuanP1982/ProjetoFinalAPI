package br.com.serratec.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Campo vazio ou nulo")
	@Column(length = 60, nullable = false)
	private String nome;
	
	private String cnpj;

	@Embedded
	private Cnpj cnpjinfo;

	public Cnpj getCnpjinfo() {
		return cnpjinfo;
	}

	public void setCnpjinfo(Cnpj cnpjinfo) {
		this.cnpjinfo = cnpjinfo;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

}
