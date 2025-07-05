package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

public  interface ILojaService {

    Loja buscarPorId(Long id);
    Loja buscarPorUsuario(Usuario usuario);
    Loja buscarPorEmailUsuario(String email);
    List<Loja> buscarTodos();
    void salvar(Loja loja);
    void excluir(Long id);
    
}
