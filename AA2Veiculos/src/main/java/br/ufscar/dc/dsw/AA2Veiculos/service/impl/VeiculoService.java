package br.ufscar.dc.dsw.AA2Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA2Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IVeiculoService;

@Service
@Transactional(readOnly = false)
public class VeiculoService implements IVeiculoService {

	@Autowired
	IVeiculoDAO dao;
	
	public void salvar(Veiculo veiculo) {
		dao.save(veiculo);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Veiculo buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public Veiculo buscarPorChassi(String chassi) {
		return dao.findByChassi(chassi);
	}
	
	@Transactional(readOnly = true)
	public Veiculo buscarPorPlaca(String placa) {
		return dao.findByPlaca(placa);
	}

	@Transactional(readOnly = true)
	public List<Veiculo> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Veiculo> buscarTodosPorModelo(String modelo){
		return dao.findAllByModelo(modelo);
	}
	
	@Transactional(readOnly = true)
	public List<Veiculo> buscarTodosPorLoja(Loja loja) {
		return dao.findAllByLoja(loja);
	}

	@Transactional(readOnly = true)
    public List<String> buscarModelos() {
		return dao.findDistinctModelos();
	}

}
