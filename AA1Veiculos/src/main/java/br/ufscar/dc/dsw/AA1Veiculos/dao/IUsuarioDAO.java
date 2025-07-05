package br.ufscar.dc.dsw.AA1Veiculos.dao;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

}

