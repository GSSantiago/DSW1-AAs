package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;

public interface IAdminService {
    Admin buscarPorEmail(String email);
}

