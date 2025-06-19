package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IVeiculoService;

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
	public List<Veiculo> buscarTodos() {
		return dao.findAll();
	}
}
