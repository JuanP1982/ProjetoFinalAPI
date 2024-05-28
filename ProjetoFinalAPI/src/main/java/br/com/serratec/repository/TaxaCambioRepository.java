package br.com.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.TaxaCambio;

public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, Long> {
	   Optional<TaxaCambio> findByMoeda(String moeda);
	    

	}