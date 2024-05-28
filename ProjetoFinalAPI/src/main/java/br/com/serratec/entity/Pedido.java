package br.com.serratec.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.serratec.enums.StatusPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* Alterado por ge
	@NotBlank(message = "Campo vazio ou nulo!")
	private String status;*/

	private Double total = 0.0;

	private LocalDate dataPedido;

	@ManyToOne
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "produto_id"))
	private Set<Produto> produtos;
	
	//ge
	   @ManyToOne
	    @JoinColumn(name = "funcionario_id")
	    private Funcionario funcionario;
	//ge
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    
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


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public LocalDate getDataPedido() {
		return dataPedido;
	}


	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
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


	

	public void setCarrinhos(Set<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}


	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public StatusPedido getStatus() {
		return status;
	}

	public Set<Carrinho> getCarrinhos() {
		return carrinhos;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	
}
