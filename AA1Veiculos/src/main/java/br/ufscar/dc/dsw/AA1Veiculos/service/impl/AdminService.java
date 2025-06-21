package br.ufscar.dc.dsw.AA1Veiculos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IAdminDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IAdminService;

@Service
@Transactional
public class AdminService implements IAdminService {

    @Autowired
    IAdminDAO dao;

    public Admin buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}  