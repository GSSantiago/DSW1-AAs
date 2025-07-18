package br.ufscar.dc.dsw.AA1Veiculos.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;


@Component
public class UniquePlacaValidator implements ConstraintValidator<UniquePlaca, String> {

    @Autowired
    private IVeiculoDAO dao;

    @Override
    public boolean isValid(String placa, ConstraintValidatorContext context) {
        if (dao != null) {
            Veiculo veiculo = dao.findByPlaca(placa);
            return veiculo == null;
        } else {
            return true;
        }
    }
}
