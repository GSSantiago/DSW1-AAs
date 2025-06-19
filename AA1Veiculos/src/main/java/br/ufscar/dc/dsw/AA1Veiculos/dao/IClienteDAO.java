package br.ufscar.dc.dsw.AA1Veiculos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Long> {

    Cliente findByEmail(String email);

    boolean existsByCpf(String cpf);
}
