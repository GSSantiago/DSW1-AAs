package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.StatusProposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;

public interface IPropostaService {

    Proposta salvar(Proposta proposta);

    List<Proposta> buscarPorCliente(Cliente cliente);

    List<Proposta> buscarPorVeiculo(Veiculo veiculo);

    List<Proposta> buscarPorVeiculoEStatus(Veiculo veiculo, StatusProposta status);

    List<Proposta> buscarPorClienteEStatus(Cliente cliente, StatusProposta status);

    Proposta buscarPropostaEmAberto(Cliente cliente, Veiculo veiculo);

    Proposta buscarPorId(Long id);
}

