package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.entity.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
}
