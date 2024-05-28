package br.com.serratec.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.dto.TaxaCambioResponseDTO;
import br.com.serratec.entity.TaxaCambio;
import br.com.serratec.exception.ResourceNotFoundException;
import br.com.serratec.repository.TaxaCambioRepository;

@Service
public class TaxaCambioService {

	private static final Logger logger = LoggerFactory.getLogger(TaxaCambioService.class);

	@Autowired
	private TaxaCambioRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${currencylayer.access_key}")
	private String accessKey;

	@Value("${currencylayer.base_url}")
	private String baseUrl;

	public TaxaCambioResponseDTO consultarMoeda(String moeda) {
		Optional<TaxaCambio> taxaCambio = repository.findByMoeda(moeda.toUpperCase());
	
		if (taxaCambio.isPresent()) {
			return new TaxaCambioResponseDTO(taxaCambio.get());
		} else {
			return consultarMoedaExterna(moeda);
		}
	}
	

	private TaxaCambioResponseDTO consultarMoedaExterna(String moeda) {
		String url = String.format("%slive?access_key=%s&currencies=%s&format=1", baseUrl, accessKey, moeda);

		try {
			ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				Map<String, Object> responseBody = response.getBody();
				logger.info("Response Body: {}", responseBody);

				if (Boolean.TRUE.equals(responseBody.get("success"))) {
					
					TaxaCambio apiExterna = mapearTaxaCambio(responseBody, moeda);
					return inserirMoeda(apiExterna);
				} else {
					String errorInfo = ((Map<String, String>) responseBody.get("error")).get("info");
					logger.warn("Taxa de conversão não encontrada para a moeda especificada: {}. Error Info: {}", moeda,
							errorInfo);
					throw new ResourceNotFoundException(
							"Taxa de conversão não encontrada para a moeda especificada: " + moeda);
				}
			} else {
				logger.error("Falha ao consultar a API externa! Status: {}", response.getStatusCode());
				throw new ResourceNotFoundException("Falha ao consultar a API externa!");
			}
		} catch (ClassCastException e) {
			logger.error("Erro ao processar a resposta da API externa: {}", e.getMessage(), e);
			throw new ResourceNotFoundException("Erro ao processar a resposta da API externa: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao consultar a API externa: {}", e.getMessage(), e);
			throw new ResourceNotFoundException("Erro ao consultar a API externa: " + e.getMessage());
		}
	}

	private TaxaCambio mapearTaxaCambio(Map<String, Object> responseBody, String moeda) {
	    TaxaCambio apiExterna = new TaxaCambio();
		apiExterna.setSucesso(true);
	    apiExterna.setMoeda(moeda);
	    apiExterna.setDataTempo(LocalDate.now());

	    @SuppressWarnings("unchecked")
	    Map<String, Double> quotes = (Map<String, Double>) responseBody.get("quotes");

	    if (quotes == null || quotes.isEmpty()) {
	        throw new ResourceNotFoundException("Cotações não encontradas para a moeda especificada: " + moeda);
	    }

	    apiExterna.setCotacoes(quotes);

	    return apiExterna;
	}
	

	public TaxaCambioResponseDTO inserirMoeda(TaxaCambio apiExterna) {
		try {
			
			return new TaxaCambioResponseDTO(repository.save(apiExterna));
		} catch (Exception e) {
			
			throw new ResourceNotFoundException("Erro ao salvar a taxa de câmbio no banco de dados: " + e.getMessage());
		}
	}

	public TaxaCambioResponseDTO atualizarMoeda(String moeda, TaxaCambio taxaCambio) {
		moeda = moeda.toUpperCase();
		Optional<TaxaCambio> existingTaxaCambio = repository.findByMoeda(moeda);
		if (existingTaxaCambio.isPresent()) {
			TaxaCambio novaTaxaCambio = existingTaxaCambio.get();
			novaTaxaCambio.setCotacoes(taxaCambio.getCotacoes());
			novaTaxaCambio.setDataTempo(taxaCambio.getDataTempo());
			novaTaxaCambio.setSucesso(taxaCambio.isSucesso());

			return new TaxaCambioResponseDTO(repository.save(novaTaxaCambio));
		} else {
			throw new ResourceNotFoundException("Moeda não encontrada!");
		}
	}

	public void deletarMoeda(String moeda) {
		moeda = moeda.toUpperCase();
		Optional<TaxaCambio> existingTaxaCambio = repository.findByMoeda(moeda);
		if (existingTaxaCambio.isPresent()) {
			repository.delete(existingTaxaCambio.get());
		} else {
			throw new ResourceNotFoundException("Moeda não encontrada!");
		}
	}
}
