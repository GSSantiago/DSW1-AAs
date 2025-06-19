package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

@SuppressWarnings("unchecked")
public interface IVeiculoDAO extends CrudRepository<Veiculo, Long>{

	Veiculo findById(long id);

	List<Veiculo> findAll();
	
	Veiculo save(Veiculo livro);

	void deleteById(Long id);
}