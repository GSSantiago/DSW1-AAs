package br.ufscar.dc.dsw.AA2Veiculos.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Imagem;

public interface IImagemDAO extends CrudRepository<Imagem, Long>{

	Imagem findById(long id);
	
    @Query("SELECT i.id FROM Imagem i")
	List<Long> findAllIds();
	
    
}