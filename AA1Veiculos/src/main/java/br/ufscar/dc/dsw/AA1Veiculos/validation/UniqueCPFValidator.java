package br.ufscar.dc.dsw.AA1Veiculos.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

    @Autowired
    private IClienteDAO dao;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (dao != null) {
            Cliente cliente = dao.findByCpf(cpf);
            return cliente == null;
        } else {
            // Pode ser true no momento de startup (sem injeção)
            return true;
        }   
    }
}