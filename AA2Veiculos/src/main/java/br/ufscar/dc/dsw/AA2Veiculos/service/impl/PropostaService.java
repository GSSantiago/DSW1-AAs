package br.ufscar.dc.dsw.AA2Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA2Veiculos.dao.IPropostaDAO;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IPropostaService;
import br.ufscar.dc.dsw.AA2Veiculos.domain.StatusProposta;

@Service
public class PropostaService implements IPropostaService{

    @Autowired
    private IPropostaDAO dao;

    @Override
    public Proposta salvar(Proposta proposta) {
        return dao.save(proposta);
    }

    @Override
    public List<Proposta> buscarPorCliente(Cliente cliente) {
        return dao.findAllByCliente(cliente);
    }

    @Override
    public Proposta buscarPropostaEmAberto(Cliente cliente, Veiculo veiculo) {
        return dao.findByClienteAndVeiculoAndStatus(cliente, veiculo, StatusProposta.ABERTO);
    }

    @Override
    public Proposta buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }
}

