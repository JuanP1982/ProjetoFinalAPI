package br.com.serratec.repository;

import br.com.serratec.entity.Funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public Page<Funcionario> findBySalarioBetween(Double valorInicial,
			Double valorFinal, Pageable pageable);
	
	public Page<Funcionario> findByNomeContainingIgnoreCase(String paramNome, Pageable pageable);
}

