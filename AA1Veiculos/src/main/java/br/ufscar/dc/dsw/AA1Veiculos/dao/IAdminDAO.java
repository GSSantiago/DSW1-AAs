package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;

public interface IAdminDAO extends CrudRepository<Admin, Long> {
    Admin findByEmail(String email);
    List<Admin> findAll();
}
