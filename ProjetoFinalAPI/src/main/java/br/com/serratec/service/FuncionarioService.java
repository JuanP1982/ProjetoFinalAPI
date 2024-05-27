package br.com.serratec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.entity.Pedido;
import br.com.serratec.exception.FuncionarioNaoPodeSerDeletadoException;
import br.com.serratec.repository.FuncionarioRepository;
import br.com.serratec.repository.PedidoRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void deletarFuncionario(Long id) throws FuncionarioNaoPodeSerDeletadoException {
        // Verificar se há pedidos associados ao funcionário
        List<Pedido> pedidos = pedidoRepository.findByFuncionarioId(id);
        if (!pedidos.isEmpty()) {
            throw new FuncionarioNaoPodeSerDeletadoException("Não é possível deletar o funcionário pois há pedidos associados.");
        }
        // Deletar o funcionário se não houver pedidos associados
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioResponseDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                           .map(FuncionarioResponseDTO::new)
                           .collect(Collectors.toList());
    }

    public Optional<FuncionarioResponseDTO> findById(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(FuncionarioResponseDTO::new);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

	
	
}
   
