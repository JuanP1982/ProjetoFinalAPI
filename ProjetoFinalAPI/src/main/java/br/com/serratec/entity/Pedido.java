package br.com.serratec.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo vazio ou nulo!")
	private String status;

	private Double total = 0.0;

	private LocalDate dataPedido;

	private String moeda;
	private Double taxa;
	
	@ManyToOne
	private Cliente cliente;

	
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Carrinho> carrinhos = new HashSet<>();

	public Set<Produto> getProdutos() {
	    Set<Produto> produtos = new HashSet<>();
	    for (Carrinho pp : carrinhos) {
	        produtos.add(pp.getId().getProduto());
	    }
	    return produtos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	@PrePersist
    public void persistDataEntrada() {
        dataPedido = LocalDate.now();
    }

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Carrinho> getCarrinhos() {
		return carrinhos;
	}

	public void setCarrinhos(Set<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public void calculaTotal() {
	    double totalPedido = 0.0;
	    
	    for (Carrinho carrinho : carrinhos) {
	        totalPedido += carrinho.getId().getProduto().getPreco() * carrinho.getId().getProduto().getQuantidade();
	    }
	    
	    this.total = totalPedido;
	
		
	}

	public Map<String, Double> cotacoes() {
		
		return cotacoes();
		
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
	//	public void calculaTotal() {
//		for (Produto produto : produtos) {
//			produto.calculaTotal();
//			this.total = total + produto.getTotal();
//		}
//	}

	
	
	

	

}
