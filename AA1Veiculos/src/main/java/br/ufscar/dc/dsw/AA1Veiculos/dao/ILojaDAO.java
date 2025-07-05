package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

@SuppressWarnings("unchecked")
public interface ILojaDAO extends CrudRepository<Loja, Long> {

    Loja findById(long id);

    Loja findByCnpj(String cnpj);

    Loja findByUsuario(Usuario usuario);
    
    Loja findByUsuarioEmail(String email);

    List<Loja> findAll();

    Loja save(Loja loja);

    void deleteById(Long id);
    
}
