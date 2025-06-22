package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

public interface ILojaDAO extends CrudRepository<Loja, Long> {
    Loja findByEmail(String email);
    List<Loja> findAll();
    Loja findByCnpj(String cnpj);
}
