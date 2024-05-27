package br.com.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByFuncionarioId(Long funcionarioId);

}
