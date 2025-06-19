package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;

public interface IClienteService {

    Cliente buscarPorId(Long id);

    Cliente buscarPorEmail(String email);

    List<Cliente> buscarTodos();

    void salvar(Cliente cliente);

    void excluir(Long id);
}
