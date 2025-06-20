package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lojas")
@PreAuthorize("hasAuthority('ADMIN')")
public class LojaController {

    @Autowired
    private ILojaService service;

    @GetMapping("/lista")
    public String lista(Model model) {
        List<Loja> lojas = service.buscarTodas();
        model.addAttribute("lojas", lojas);
        return "loja/lista";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("loja", new Loja());
        return "loja/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("loja") Loja loja, BindingResult result) {
        if (result.hasErrors()) {
            return "loja/cadastro";
        }
        service.salvar(loja);
        return "redirect:/lojas/lista";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, Model model) {
        Loja loja = service.buscarPorId(id);
        model.addAttribute("loja", loja);
        return "loja/cadastro";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        service.excluir(id);
        return "redirect:/lojas/lista";
    }
}
