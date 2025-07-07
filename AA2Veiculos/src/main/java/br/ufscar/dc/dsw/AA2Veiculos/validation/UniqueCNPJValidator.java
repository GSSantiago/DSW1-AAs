package br.ufscar.dc.dsw.AA2Veiculos.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.AA2Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;


@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

    @Autowired
    private ILojaDAO dao;

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (dao != null) {
            Loja loja = dao.findByCnpj(cnpj);
            return loja == null;
        } else {
            // Pode ser true no momento de startup (sem injeção)
            return true;
        }
    }
}
