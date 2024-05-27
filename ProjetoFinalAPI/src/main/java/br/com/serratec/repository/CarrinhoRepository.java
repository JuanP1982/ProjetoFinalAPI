package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Carrinho;
import br.com.serratec.entity.PedidoProdutoPK;

public interface CarrinhoRepository extends JpaRepository<Carrinho, PedidoProdutoPK> {

}
