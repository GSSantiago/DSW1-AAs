package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IClienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public String listar(Model model) {
        List<Cliente> lista = clienteService.buscarTodos();
        model.addAttribute("clientes", lista);
        return "cliente/lista"; 
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente) {
        return "cliente/cadastro"; 
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String atualizar(@Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        clienteService.salvar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente.temPropostasEmAberto()) {
            attr.addFlashAttribute("error", "Não é possível remover o cliente pois há propostas em aberto.");
            return "redirect:/clientes/listar";
        }
        clienteService.excluir(id);
        attr.addFlashAttribute("sucess", "Cliente removido com sucesso.");
        return "redirect:/clientes/listar";
    }
    
}
