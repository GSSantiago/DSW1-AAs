package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
@PreAuthorize("hasAuthority('ADMIN')")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @GetMapping("/lista")
    public String lista(Model model) {
        List<Cliente> clientes = service.buscarTodos();
        model.addAttribute("clientes", clientes);
        return "cliente/lista";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/form";
        }
        service.salvar(cliente);
        return "redirect:/clientes/lista";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = service.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/form";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        service.excluir(id);
        return "redirect:/clientes/lista";
    }
}
