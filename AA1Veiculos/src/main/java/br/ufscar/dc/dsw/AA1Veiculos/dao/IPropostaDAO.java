package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA1Veiculos.domain.StatusProposta;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

public interface IPropostaDAO extends CrudRepository<Proposta, Long> {

    List<Proposta> findAllByCliente(Cliente cliente);

    List<Proposta> findAllByVeiculoLoja(Loja loja);

    List<Proposta> findAllByVeiculoLojaAndStatus(Loja loja, StatusProposta status);

    List<Proposta> findAllByVeiculoAndStatus(Veiculo veiculo, StatusProposta status);

    Proposta findByClienteAndVeiculoAndStatus(Cliente cliente, Veiculo veiculo, br.ufscar.dc.dsw.AA1Veiculos.domain.StatusProposta status);

    boolean existsByClienteAndVeiculoAndStatus(Cliente cliente, Veiculo veiculo, StatusProposta status);
}
