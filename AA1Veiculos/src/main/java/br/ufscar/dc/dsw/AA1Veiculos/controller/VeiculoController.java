package br.ufscar.dc.dsw.AA1Veiculos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA1Veiculos.security.UsuarioDetails;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IImagemService;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IVeiculoService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private IVeiculoService veiculoService;

    @Autowired
    private ILojaService lojaService;
    
    @Autowired
    private IImagemService imagemService;
    
    @GetMapping
    public String redirect() {
        return "redirect:/veiculos/listar";
    }

    @GetMapping("/cadastrar")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String cadastrar(Veiculo veiculo) {
        return "veiculo/cadastro";
    }

    @GetMapping("/listar")
    public String listar(@RequestParam(required = false) String modelo, ModelMap model) {
        if (modelo == null || modelo.isBlank()) {
            model.addAttribute("veiculos", veiculoService.buscarTodos());
        } else {
            model.addAttribute("veiculos", veiculoService.buscarTodosPorModelo(modelo));
        }
        return "veiculo/lista";
    }

    @GetMapping("/meus")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String listarVeiculosDaLoja(ModelMap model, @AuthenticationPrincipal UsuarioDetails user) {
        // Debug de usuário e authorities
        System.out.println("Loja logada: " + user.getUsername());
        System.out.println("Authorities atuais: " + user.getAuthorities());

        Loja loja = lojaService.buscarPorEmail(user.getUsername());
        List<Veiculo> veiculos = veiculoService.buscarTodosPorLoja(loja);
        model.addAttribute("veiculos", veiculos);
        return "veiculo/listaLoja"; 
    }

	@PostMapping("/salvar")
	@PreAuthorize("hasAuthority('ROLE_LOJA', 'ROLE_ADMIN')")
	public String salvar(@Valid Veiculo veiculo, BindingResult result,
			@RequestParam(required = false) List<MultipartFile> arquivos,
			ModelMap model, RedirectAttributes attr) {

		if (result.hasErrors()) {
			System.out.println("Erros de validação encontrados:");
			result.getAllErrors().forEach(error -> System.out.println(error));
			return "veiculo/cadastro";
		}

		List<Imagem> imagens = new ArrayList<>();
		for (MultipartFile arquivo : arquivos) {
			if (arquivo != null && !arquivo.isEmpty()) {
				String contentType = arquivo.getContentType();

				if (!("image/jpeg".equalsIgnoreCase(contentType) || "image/jpg".equalsIgnoreCase(contentType)
						|| "image/png".equalsIgnoreCase(contentType))) {
					model.addAttribute("fail", "invalid.type.message");
					return "veiculo/cadastro";
				}
				try {
					Imagem img = new Imagem();
					img.setNome(arquivo.getOriginalFilename());
					img.setContentType(contentType);
					img.setDados(arquivo.getBytes());
					img.setVeiculo(veiculo);
					imagens.add(img);
				} catch (IOException e) {
					model.addAttribute("fail", "default.message");
					return "veiculo/cadastro";
				}
			}
		}
		veiculo.setImagens(imagens);

		veiculoService.salvar(veiculo);
		attr.addFlashAttribute("sucess", "veiculo.create.sucess");
		return "redirect:/veiculos/listar";
	}

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("veiculo", veiculoService.buscarPorId(id));
        return "veiculo/cadastro";
    }

    @PostMapping("/editar")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String editar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "veiculo/cadastro";
        }
        veiculoService.salvar(veiculo);
        attr.addFlashAttribute("sucess", "veiculo.edit.sucess");
        return "redirect:/veiculos/listar";
    }

    @GetMapping("/excluir/{id}")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        veiculoService.excluir(id);
        attr.addFlashAttribute("sucess", "veiculo.delete.sucess");
        return "redirect:/veiculos/listar";
    }

    @ModelAttribute("lojas")
    public List<Loja> listaLojas() {
        return lojaService.buscarTodos();
    }
    
    @ModelAttribute("modelos")
    public List<String> listaModelos() {
        return veiculoService.buscarModelos();
    }
    
    @ModelAttribute("imagens")
    public List<Long> listaImagens() {
        return imagemService.buscarTodosPorId();
    }
}
