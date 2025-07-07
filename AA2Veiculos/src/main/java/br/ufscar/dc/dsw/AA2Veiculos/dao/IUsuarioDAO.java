package br.ufscar.dc.dsw.AA2Veiculos.dao;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
