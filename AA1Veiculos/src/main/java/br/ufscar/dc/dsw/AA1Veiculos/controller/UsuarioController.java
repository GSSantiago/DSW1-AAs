package br.ufscar.dc.dsw.AA1Veiculos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Usuario usuario) {
        return "usuario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("usuarios", service.buscarTodos());
        return "usuario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "usuario/cadastro";
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        service.salvar(usuario);
        attr.addFlashAttribute("sucess", "usuario.create.sucess");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("usuario", service.buscarPorId(id));
        return "usuario/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Usuario usuario, String novoSenha, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "usuario/cadastro";
        }
        if (novoSenha != null && !novoSenha.trim().isEmpty()) {
            usuario.setSenha(encoder.encode(novoSenha));
        } else {
			System.out.println("Senha não foi editada");
		}
        service.salvar(usuario);
        attr.addFlashAttribute("sucess", "usuario.edit.sucess");
        return "redirect:/usuarios/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
        service.excluir(id);
        model.addAttribute("sucess", "usuario.delete.sucess");
        return listar(model);
    }
}