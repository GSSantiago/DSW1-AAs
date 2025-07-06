package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long> {
    Cliente findByEmail(String email);
    List<Cliente> findAll();
}
