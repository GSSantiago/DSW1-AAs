package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteDAO dao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void salvar(Cliente cliente) {
        if (!cliente.getSenha().startsWith("$2a$")) { // verifica se já está criptografada
            cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        }   
        dao.save(cliente);
    }

    @Override
    public void excluir(Long id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}
