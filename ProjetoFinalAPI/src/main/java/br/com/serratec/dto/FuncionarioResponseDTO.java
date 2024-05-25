package br.com.serratec.dto;

import java.util.List;

import br.com.serratec.entity.Funcionario;
import br.com.serratec.entity.Pedido;

public class FuncionarioResponseDTO {

    private String nome;
    private String email;
    private List<Pedido> pedidos;

    public FuncionarioResponseDTO() {}

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.nome = funcionario.getNome();
        this.email = funcionario.getEmail();
        this.pedidos = funcionario.getPedidos();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

