package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;

public interface IImagemService {
	
	Imagem buscarPorId(Long id);
	
	List<Long> buscarTodosPorId();

}
