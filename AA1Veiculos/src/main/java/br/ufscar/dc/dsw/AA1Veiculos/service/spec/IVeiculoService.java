package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

public interface IVeiculoService {

    Veiculo buscarPorId(Long id);
    
    List<Veiculo> buscarTodosPorModelo(String modelo);

    List<Veiculo> buscarTodos();
    
    List<String> buscarModelos();

    void salvar(Veiculo veiculo);

    void excluir(Long id);
}
