package br.ufscar.dc.dsw.AA1Veiculos.dao;


import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;

public interface IImagemDAO extends CrudRepository<Imagem, Long>{

	Imagem findById(long id);
	
    
}