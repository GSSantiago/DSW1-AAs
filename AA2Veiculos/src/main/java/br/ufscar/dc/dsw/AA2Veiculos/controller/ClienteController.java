package br.ufscar.dc.dsw.AA2Veiculos.controller;

import java.util.List;
//import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.validation.annotation.Validated;
import br.ufscar.dc.dsw.AA2Veiculos.validation.ValidationGroups;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IClienteService;
//import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;

//import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // @Autowired
    // private MessageSource messageSource;

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
    public String salvar(
            @Validated(ValidationGroups.OnCreate.class) Cliente cliente,
            BindingResult result,
            RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        try {
            clienteService.salvar(cliente);
            attr.addFlashAttribute("sucess", "mensagem.sucesso.cliente.salvo");
        } catch (DataIntegrityViolationException e) {
            attr.addFlashAttribute("fail", "mensagem.erro.cliente.documento.duplicado");
        }
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/cadastro";
    }

    @PostMapping("/editar")
    public String atualizar(
            @Validated(ValidationGroups.OnUpdate.class) Cliente cliente,
            BindingResult result,
            RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }
        try {
            clienteService.salvar(cliente);
            attr.addFlashAttribute("sucess", "mensagem.sucesso.cliente.atualizado");
        } catch (DataIntegrityViolationException e) {
            attr.addFlashAttribute("fail", "mensagem.erro.cliente.documento.duplicado");
        }
        return "redirect:/clientes";
    }

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
