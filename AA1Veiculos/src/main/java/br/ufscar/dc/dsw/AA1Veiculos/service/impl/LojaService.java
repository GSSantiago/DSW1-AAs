package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

@Service
@Transactional(readOnly = false)
public class LojaService implements ILojaService {

    @Autowired
    private ILojaDAO dao;


    @Transactional(readOnly = true)
	public Loja buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

    @Transactional(readOnly = true)
    public List<Loja> buscarTodos() {
        return dao.findAll();
    }

    public void salvar(Loja loja) {
        dao.save(loja);
    }

    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Loja buscarPorUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }

    @Transactional(readOnly = true)
    public Loja buscarPorEmailUsuario(String email) {
        return dao.findByUsuarioEmail(email);
    }

}