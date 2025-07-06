package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IAdminDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDAO dao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Admin buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Admin buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public List<Admin> buscarTodos() {
        return dao.findAll();
    }

    @Override
    public void salvar(Admin admin) {
        if (!admin.getSenha().startsWith("$2a$")) {
            admin.setSenha(passwordEncoder.encode(admin.getSenha()));
        }
        dao.save(admin);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }
}

