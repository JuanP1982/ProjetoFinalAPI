package br.com.serratec.service;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.FavoritoDTO;
import br.com.serratec.entity.Favorito;
import br.com.serratec.repository.FavoritoRepository;

//@Service
//public class FavoritoService {
//
//	@Autowired
//	private FavoritoRepository repository;
//	
//	@Autowired
//	private ProdutoService produtoService;
//	
//	
//
//	// listarc
//	public List<FavoritoDTO> listar() {
//		List <Favorito> favoritos = repository.findAll();
//		
//		
//		 return favoritos.stream().map((p) -> new FavoritoDTO(p)).collect(Collectors.toList());
//	}
//	
//	public FavoritoDTO listarId(Long id) {
//	    Favorito favorito = repository.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Favorito não encontrado!"));
//	    FavoritoDTO favoritoResponseDTO = new FavoritoDTO(favorito);
//
//	    return favoritoResponseDTO;
//	}
//	
//
//	// inserir
//	  public FavoritoDTO inserir(@Valid FavoritoRequestDTO favoritoRequestDTO) {
//	        Favorito favorito = new Favorito();
//	        
//	        favorito.setCliente(favoritoRequestDTO.getCliente());
//	        favorito.setStatus(favoritoRequestDTO.getStatus());
//	        
//	        Set<Carrinho> carrinhos = new HashSet<>();
//	        
//	        
//	        for (Long produtoId : favoritoRequestDTO.getProdutoIds()) {
//	            Produto produto = produtoService.listarId(produtoId);
//	            
//	            carrinhos.add(new Carrinho(favorito, produto));
//	        }
//	        favorito.setCarrinhos(carrinhos);
//	        repository.save(favorito);
//	        FavoritoDTO response = new FavoritoDTO(favorito);
//	        return response;
//	    }
//
//	// atualizar
//
//	public Favorito atualizar(Long id, @Valid Favorito favorito) {
//		if (repository.existsById(id)) {
//			favorito.setId(id);
//			repository.save(favorito);
//		}
//		throw new ResourceNotFoundException("Favorito com o id: " + id + " não encontrado!");
//	}
//
//	// deletar
//	public ResponseEntity<String> deletar(Long id) {
//		if (repository.existsById(id)) {
//			repository.deleteById(id);
//			return ResponseEntity.status(HttpStatus.SC_OK).body("Favorito deletado com sucesso!");
//		}
//		throw new ResourceNotFoundException("Favorito com o id: " + id + " não encontrado!");
//	}
//}

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRepository;
    
 
    public FavoritoDTO salvar(Favorito favorito) {
    	FavoritoDTO dto = new FavoritoDTO(favorito);
        favoritoRepository.save(favorito);
        return dto;
    }

    
    public List<FavoritoDTO> listarTodos() {
    	 List<Favorito> fav = favoritoRepository.findAll();
    	 return fav.stream().map((f)->new FavoritoDTO(f)).collect(Collectors.toList());
    }
}
