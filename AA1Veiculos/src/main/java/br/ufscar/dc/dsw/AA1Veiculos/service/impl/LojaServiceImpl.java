package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

@Service
public class LojaServiceImpl implements ILojaService {

    @Autowired
    private ILojaDAO dao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void salvar(Loja loja) {
        if (!loja.getSenha().startsWith("$2a$")) {
            loja.setSenha(passwordEncoder.encode(loja.getSenha()));
        }
        dao.save(loja);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Loja buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Loja buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public Loja buscarPorCnpj(String cnpj) {
        return dao.findByCnpj(cnpj);
    }

    @Override
    public List<Loja> buscarTodos() {
        return dao.findAll();
    }
}
