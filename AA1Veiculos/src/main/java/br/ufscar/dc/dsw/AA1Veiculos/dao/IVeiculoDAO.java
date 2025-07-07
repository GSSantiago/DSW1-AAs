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
	
	Veiculo findByPlaca(String placa);

	@Query("SELECT v FROM Veiculo v LEFT JOIN Proposta p ON v = p.veiculo WHERE (p IS NULL OR p.status != 'ACEITO')")
	List<Veiculo> findAll();
	
	Veiculo save(Veiculo livro);

	void deleteById(Long id);
	
    @Query("SELECT DISTINCT v.modelo FROM Veiculo v LEFT JOIN Proposta p ON v = p.veiculo WHERE (p IS NULL OR p.status != 'ACEITO')")
    List<String> findDistinctModelos();

	@Query("SELECT v FROM Veiculo v LEFT JOIN Proposta p ON v = p.veiculo WHERE v.modelo = :modelo AND (p IS NULL OR p.status != 'ACEITO')")
    List<Veiculo> findAllByModelo(String modelo);

	@Query("SELECT v FROM Veiculo v LEFT JOIN Proposta p ON v = p.veiculo WHERE v.loja = :loja AND (p IS NULL OR p.status != 'ACEITO')")
	List<Veiculo> findAllByLoja(Loja loja);
    
}