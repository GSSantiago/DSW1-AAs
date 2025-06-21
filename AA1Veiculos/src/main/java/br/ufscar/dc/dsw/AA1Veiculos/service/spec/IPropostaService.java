package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

public interface IPropostaService {

    Proposta salvar(Proposta proposta);

    List<Proposta> buscarPorCliente(Cliente cliente);

    Proposta buscarPropostaEmAberto(Cliente cliente, Veiculo veiculo);

    Proposta buscarPorId(Long id);
}

