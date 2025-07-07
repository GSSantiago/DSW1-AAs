package br.ufscar.dc.dsw.AA2Veiculos.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IVeiculoService;

@Component
public class VeiculoConversor implements Converter<String, Veiculo> {

    @Autowired
    private IVeiculoService service;

    @Override
    public Veiculo convert(String text) {
        if (text.isEmpty()) {
            return null;
        }
        Long id = Long.valueOf(text);
        return service.buscarPorId(id);
    }
}
