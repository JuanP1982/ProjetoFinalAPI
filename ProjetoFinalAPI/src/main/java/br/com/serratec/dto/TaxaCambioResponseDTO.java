package br.com.serratec.dto;

import java.time.LocalDate;
import java.util.Map;

import br.com.serratec.entity.TaxaCambio;

public class TaxaCambioResponseDTO {
	private boolean sucesso;
	private LocalDate dataTempo;
	private String moeda;
	private Map<String, Double> cotacoes;

	public TaxaCambioResponseDTO(TaxaCambio taxaCambio) {
		this.sucesso = taxaCambio.isSucesso();
		this.dataTempo = taxaCambio.getDataTempo();
		this.moeda = taxaCambio.getMoeda();
		this.cotacoes = taxaCambio.getCotacoes();
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public LocalDate getDataTempo() {
		return dataTempo;
	}

	public String getMoeda() {
		return moeda;
	}

	public Map<String, Double> getCotacoes() {
		return cotacoes;
	}
}