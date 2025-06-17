package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Proposta;

public interface IPropostaService {

    Proposta buscarPorId(Long id);

    List<Proposta> buscarPorCliente(Long clienteId);

    List<Proposta> buscarPorVeiculo(Long veiculoId);

    void salvar(Proposta proposta);

    void atualizarStatus(Long id, String status, String mensagem);

    boolean existePropostaAberta(Long clienteId, Long veiculoId);

}
