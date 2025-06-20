package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

@Service
@Transactional(readOnly = false)
public class LojaService implements ILojaService {

	@Autowired
	ILojaDAO dao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void salvar(Loja loja) {
		if (!loja.getSenha().startsWith("$2a$")) { 
			loja.setSenha(passwordEncoder.encode(loja.getSenha()));
		}
		dao.save(loja);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Loja buscarPorCNPJ(String CNPJ) {
		return dao.findByCnpj(CNPJ);
	}
	
	@Transactional(readOnly = true)
	public Loja buscarPorId(Long id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public List<Loja> buscarTodas() {
		return dao.findAll();
	}

	@Transactional(readOnly = true)
	public Loja buscarPorEmail(String email) {
		return dao.findByEmail(email);
	}

}
