package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

public interface ILojaService {

    Loja buscarPorCNPJ(String CNPJ);
    
    Loja buscarPorId(Long id);

    List<Loja> buscarTodas();

    Loja buscarPorEmail(String email);

    void salvar(Loja loja);

    void excluir(Long id);
}
