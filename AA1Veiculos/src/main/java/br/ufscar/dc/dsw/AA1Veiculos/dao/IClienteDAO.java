package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IClienteDAO extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

    Cliente save(Cliente cliente);

    void deleteById(Long id);

    Cliente findByUsuario(Usuario usuario);
    
    Cliente findByUsuarioEmail(String email);
}