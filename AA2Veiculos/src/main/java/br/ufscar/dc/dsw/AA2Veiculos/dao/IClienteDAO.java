package br.ufscar.dc.dsw.AA2Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {
    Cliente findByEmail(String email);
    List<Cliente> findAll();
}
