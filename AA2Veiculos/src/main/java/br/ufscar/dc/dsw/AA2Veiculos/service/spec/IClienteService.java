package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;

public interface IClienteService {
    Cliente buscarPorId(Long id);
    Cliente buscarPorEmail(String email);
    List<Cliente> buscarTodos();
    void salvar(Cliente cliente);
    void excluir(Long id);
}
