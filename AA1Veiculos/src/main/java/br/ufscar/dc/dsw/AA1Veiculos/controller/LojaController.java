package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private ILojaService lojaService;

    @GetMapping
    public String listar(Model model) {
        List<Loja> lista = lojaService.buscarTodos();
        model.addAttribute("lojas", lista);
        return "loja/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Loja loja) {
        return "loja/cadastro"; 
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Loja loja, BindingResult result) {
        if (result.hasErrors()) {
            return "loja/cadastro";
        }
        lojaService.salvar(loja);
        return "redirect:/lojas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Loja loja = lojaService.buscarPorId(id);
        model.addAttribute("loja", loja);
        return "loja/cadastro"; 
    }

    @PostMapping("/editar")
    public String atualizar(@Valid Loja loja, BindingResult result) {
        if (result.hasErrors()) {
            return "loja/cadastro";
        }
        lojaService.salvar(loja);
        return "redirect:/lojas";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        lojaService.excluir(id);
        return "redirect:/lojas";
    }
}
