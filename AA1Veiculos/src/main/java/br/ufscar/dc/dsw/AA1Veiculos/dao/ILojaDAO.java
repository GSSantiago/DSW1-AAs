package br.ufscar.dc.dsw.AA1Veiculos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

@Repository
public interface ILojaDAO extends JpaRepository<Loja, Long> {
    Loja findByCnpj(String cnpj);
    Loja findByEmail(String email);
    Loja findById(long id);
}
