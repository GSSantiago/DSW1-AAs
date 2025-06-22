package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IImagemDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;

import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IImagemService;

@Service
@Transactional(readOnly = false)
public class ImagemService implements IImagemService {

	@Autowired
	IImagemDAO dao;
	
	@Transactional(readOnly = true)
	public Imagem buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
    public List<Long> buscarTodosPorId() {
		return dao.findAllIds();
	}
}
