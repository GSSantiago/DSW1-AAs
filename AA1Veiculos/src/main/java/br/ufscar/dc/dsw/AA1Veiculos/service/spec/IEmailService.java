package br.ufscar.dc.dsw.AA1Veiculos.service.spec;

public interface IEmailService {
    void enviarEmail(String to, String assunto, String corpo);
}
