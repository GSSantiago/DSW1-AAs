package br.ufscar.dc.dsw.AA2Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.StatusProposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;

public interface IPropostaDAO extends CrudRepository<Proposta, Long> {

    List<Proposta> findAllByCliente(Cliente cliente);

    List<Proposta> findAllByVeiculoLoja(Loja loja);

    List<Proposta> findAllByVeiculoLojaAndStatus(Loja loja, StatusProposta status);

    List<Proposta> findAllByVeiculoAndStatus(Veiculo veiculo, StatusProposta status);

    Proposta findByClienteAndVeiculoAndStatus(Cliente cliente, Veiculo veiculo, br.ufscar.dc.dsw.AA2Veiculos.domain.StatusProposta status);

    boolean existsByClienteAndVeiculoAndStatus(Cliente cliente, Veiculo veiculo, StatusProposta status);
}
