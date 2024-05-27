package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.FotoProduto;
import br.com.serratec.entity.Produto;

public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {
	public Optional<FotoProduto> findByProduto(Produto produto);
}