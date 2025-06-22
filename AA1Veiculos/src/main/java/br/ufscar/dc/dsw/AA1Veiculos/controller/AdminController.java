package br.ufscar.dc.dsw.AA1Veiculos.controller;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@Controller
@RequestMapping("/admins")
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    private IAdminService service;

    @GetMapping("/lista")
    public String lista(Model model, Authentication auth) {
        System.out.println("Authorities atuais: " + auth.getAuthorities());
        List<Admin> admins = service.buscarTodos();
        model.addAttribute("admins", admins);
        return "admin/lista";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("admins", service.buscarTodos());
        return "admin/lista";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Admin admin) {
        return "admin/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/formulario";
        }
        service.salvar(admin);
        return "redirect:/admins";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("admin", service.buscarPorId(id));
        return "admin/formulario";
    }

    @PostMapping("/editar")
    public String atualizar(@Valid Admin admin, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/formulario";
        }
        service.salvar(admin);
        return "redirect:/admins";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id) {
        service.excluir(id);
        return "redirect:/admins";
    }
}
