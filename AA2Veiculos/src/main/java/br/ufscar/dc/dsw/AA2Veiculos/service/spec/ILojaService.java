package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;

public interface ILojaService {
    Loja buscarPorId(Long id);
    Loja buscarPorEmail(String email);
    List<Loja> buscarTodos();
    void salvar(Loja loja);
    void excluir(Long id);
    Loja buscarPorCnpj(String cnpj);
}
