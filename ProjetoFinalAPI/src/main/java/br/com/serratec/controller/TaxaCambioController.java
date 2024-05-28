package br.com.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.TaxaCambioResponseDTO;
import br.com.serratec.entity.TaxaCambio;
import br.com.serratec.service.TaxaCambioService;

@RestController
@RequestMapping("/Cambio")

public class TaxaCambioController {

	@Autowired
	private TaxaCambioService service;

	@GetMapping("/{moeda}")
	public ResponseEntity<TaxaCambioResponseDTO> consultarMoeda(@PathVariable String moeda) {
		TaxaCambioResponseDTO dto = service.consultarMoeda(moeda);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<TaxaCambioResponseDTO> inserirMoeda(@RequestBody TaxaCambio taxaCambio) {
		TaxaCambioResponseDTO dto = service.inserirMoeda(taxaCambio);
		return ResponseEntity.ok(dto);
	}

	@PutMapping("/{moeda}")
	public ResponseEntity<TaxaCambioResponseDTO> atualizarMoeda(@PathVariable String moeda,
			@RequestBody TaxaCambio taxaCambio) {
		TaxaCambioResponseDTO dto = service.atualizarMoeda(moeda, taxaCambio);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping("/{moeda}")
	public ResponseEntity<Void> deletarMoeda(@PathVariable String moeda) {
		service.deletarMoeda(moeda);
		return ResponseEntity.noContent().build();
	}
}
