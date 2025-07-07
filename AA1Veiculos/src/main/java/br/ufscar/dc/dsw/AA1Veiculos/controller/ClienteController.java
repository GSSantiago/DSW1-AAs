package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IClienteService;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private MessageSource messageSource;

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
            System.out.println("Erros de binding: " + result.getAllErrors());
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

 /*   @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        clienteService.excluir(id);
        return "redirect:/clientes";
    } 
*/

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            clienteService.excluir(id);
            attr.addFlashAttribute("sucess", "mensagem.sucesso.cliente.removido");
        } catch (DataIntegrityViolationException e) {
            attr.addFlashAttribute("fail", "mensagem.falha.cliente");
        }
        return "redirect:/clientes";
    }

}
