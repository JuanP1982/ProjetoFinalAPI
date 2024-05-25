package br.com.serratec.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.FuncionarioResponseDTO;
import br.com.serratec.entity.Funcionario;
import br.com.serratec.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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

    public void deleteById(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
