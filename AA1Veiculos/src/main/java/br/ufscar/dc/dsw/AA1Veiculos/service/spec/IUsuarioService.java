package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import java.util.List;

public interface IUsuarioService {
    Usuario buscarPorId(Long id);
    Usuario buscarPorEmail(String email);
    List<Usuario> buscarTodos();
    void salvar(Usuario usuario);
    void excluir(Long id);
}
