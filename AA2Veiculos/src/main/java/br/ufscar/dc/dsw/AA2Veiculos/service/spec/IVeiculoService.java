package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;

public interface IVeiculoService {

    Veiculo buscarPorId(Long id);
    
    Veiculo buscarPorChassi(String chassi);
    
    Veiculo buscarPorPlaca(String placa);
    
    List<Veiculo> buscarTodosPorModelo(String modelo);

    List<Veiculo> buscarTodos();
    
    List<String> buscarModelos();

    List<Veiculo> buscarTodosPorLoja(Loja loja);

    void salvar(Veiculo veiculo);

    void excluir(Long id);
}
