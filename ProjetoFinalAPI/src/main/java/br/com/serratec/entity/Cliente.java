package br.com.serratec.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message= "Campo vazio ou nulo")
	@Column(length = 60, nullable=false)
	private String nome;
	
	@NotBlank(message= "Campo vazio ou nulo")
	@Pattern(regexp = "(\\d{2} )\\d{5}-\\d{4}",message = "Telefone invalido")
	@Column(length = 13,nullable = false)
	private String telefone;
	
	@Email(message="Email invalido")
	@NotBlank(message= "Campo vazio ou nulo")
	@Column(nullable = false)
	private String email;
	
	
	@CPF(message= "CPF invalido")
	@NotBlank(message= "Campo vazio ou nulo")
	@Column(nullable = false, length = 14)
	private String cpf;
	
	@Column(nullable = false)
	@NotBlank(message= "Campo vazio ou nulo")
	private String senha;
	
	@NotBlank
	@Pattern(regexp = "(\\d{5})-\\d{3}",message = "Cep invalido")
	@Size(max = 9, message = " Tamanho inválido!")
	private String cep;

	@Embedded
	private Endereco endereco;
	
	@JsonBackReference
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private Set<Pedido> pedidos = new HashSet<>();
	

	
	
	

	@Override
	public String toString() {
		return "nome: " + nome + "\n telefone: " + telefone + "\n email: " + email;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
