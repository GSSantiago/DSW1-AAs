package br.ufscar.dc.dsw.AA2Veiculos.controller;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Proposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.StatusProposta;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IClienteService;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IPropostaService;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IVeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

//import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
public class PropostaRestController {

    @Autowired
    private IPropostaService propostaService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IVeiculoService veiculoService;



    @GetMapping(path = "propostas/cliente/{id}/status/{status}")
    public ResponseEntity<List<Proposta>> buscarPorClienteEStatus(
            @PathVariable Long id,
            @PathVariable StatusProposta status) {

        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }

        List<Proposta> propostas = propostaService.buscarPorClienteEStatus(cliente, status);
        return ResponseEntity.ok(propostas);
    }

    @GetMapping(path = "propostas/veiculo/{id}/status/{status}")
    public ResponseEntity<List<Proposta>> buscarPorVeiculoEStatus(
            @PathVariable Long id,
            @PathVariable StatusProposta status) {

        Veiculo veiculo = veiculoService.buscarPorId(id);
        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }

        List<Proposta> propostas = propostaService.buscarPorVeiculoEStatus(veiculo, status);
        return ResponseEntity.ok(propostas);
    }
}
