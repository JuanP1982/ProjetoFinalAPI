package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Loja;

public interface LojaRepository  extends JpaRepository<Loja, Long> {

}
