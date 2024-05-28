package br.com.serratec.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

<<<<<<< HEAD
import br.com.serratec.enums.StatusPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
=======
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e
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

	/* Alterado por ge
	@NotBlank(message = "Campo vazio ou nulo!")
	private String status;*/

	private Double total = 0.0;

	private LocalDate dataPedido;

	@ManyToOne
	private Cliente cliente;

<<<<<<< HEAD
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
    
=======
	@OneToMany(mappedBy = "id.pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Carrinho> carrinhos = new HashSet<>();
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e

	public Set<Produto> getProdutos() {
		Set<Produto> produtos = new HashSet<>();
		for (Carrinho pp : carrinhos) {
			produtos.add(pp.getId().getProduto());
		}
<<<<<<< HEAD
	}

=======
		return produtos;
	}
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

<<<<<<< HEAD
=======
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e

	public Double getTotal() {
		return total;
	}

<<<<<<< HEAD

=======
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e
	public void setTotal(Double total) {
		this.total = total;
	}

<<<<<<< HEAD

=======
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e
	public LocalDate getDataPedido() {
		return dataPedido;
	}

<<<<<<< HEAD

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
=======
	@PrePersist
	public void persistDataEntrada() {
		dataPedido = LocalDate.now();
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

<<<<<<< HEAD

	public Set<Produto> getProdutos() {
		return produtos;
=======
	public Set<Carrinho> getCarrinhos() {
		return carrinhos;
	}

	public void setCarrinhos(Set<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
>>>>>>> 2cad70f7245eb8d8409e519d4e719c9486b52e7e
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


	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	
}
