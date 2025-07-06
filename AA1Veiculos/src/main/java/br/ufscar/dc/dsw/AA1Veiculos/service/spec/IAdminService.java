package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;

public interface IAdminService {
    Admin buscarPorId(Long id);
    Admin buscarPorEmail(String email);
    List<Admin> buscarTodos();
    void salvar(Admin admin);
    void excluir(Long id);
}
