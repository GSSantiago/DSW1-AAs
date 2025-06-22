package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

public interface IUsuarioService {
    Usuario buscarPorEmail(String email);
}
