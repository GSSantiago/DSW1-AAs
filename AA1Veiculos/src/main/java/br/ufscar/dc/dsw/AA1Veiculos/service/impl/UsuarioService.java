package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public Usuario buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return (List<Usuario>) dao.findAll();
    }

    @Override
    public void salvar(Usuario usuario) {
        dao.save(usuario);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
}