package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;
import org.springframework.context.MessageSource;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private ILojaService lojaService;

    @Autowired
    private MessageSource messageSource;

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
    public String remover(@PathVariable("id") Long id, RedirectAttributes attr) {
        try {
            lojaService.excluir(id);
            attr.addFlashAttribute("sucess", "mensagem.sucesso.loja.removida");
        } catch (DataIntegrityViolationException e) {
            attr.addFlashAttribute("fail", "mensagem.falha.loja.vinculada");
        }
        return "redirect:/lojas";
    }

}
