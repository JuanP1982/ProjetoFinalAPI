package br.com.serratec.entity;

import java.time.LocalDate;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;

@Entity
public class TaxaCambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean sucesso;
	private LocalDate dataTempo;
	private String moeda;

	@ElementCollection
	@CollectionTable(name = "cotacoes")
	@MapKeyColumn(name = "moeda")
	@Column(name = "valor")
	private Map<String, Double> cotacoes;

	// Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}

	public LocalDate getDataTempo() {
		return dataTempo;
	}

	public void setDataTempo(LocalDate dataTempo) {
		this.dataTempo = dataTempo;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public Map<String, Double> getCotacoes() {
		return cotacoes;
	}

	public void setCotacoes(Map<String, Double> cotacoes) {
		this.cotacoes = cotacoes;

	}

}