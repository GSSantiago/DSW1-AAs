package br.ufscar.dc.dsw.AA2Veiculos.service.spec;

public interface IEmailService {
    void enviarEmail(String to, String assunto, String corpo);
}
