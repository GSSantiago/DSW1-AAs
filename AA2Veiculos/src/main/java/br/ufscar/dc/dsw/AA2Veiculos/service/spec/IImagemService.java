package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Imagem;

public interface IImagemService {
	
	Imagem buscarPorId(Long id);
	
	List<Long> buscarTodosPorId();

}
