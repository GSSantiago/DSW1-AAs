package br.ufscar.dc.dsw.AA1Veiculos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;

@SuppressWarnings("unchecked")
public interface IVeiculoDAO extends CrudRepository<Veiculo, Long>{

	Veiculo findById(long id);
	
	Veiculo findByChassi(String chassi);

	List<Veiculo> findAll();
	
	Veiculo save(Veiculo livro);

	void deleteById(Long id);
	
    @Query("SELECT DISTINCT v.modelo FROM Veiculo v")
    List<String> findDistinctModelos();
    
    List<Veiculo> findAllByModelo(String modelo);

	List<Veiculo> findAllByLoja(Loja loja);
    
}