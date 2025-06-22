package br.ufscar.dc.dsw.AA1Veiculos.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IVeiculoService;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.ILojaService;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private IVeiculoService veiculoService;

	@Autowired
	private ILojaService lojaService;
	
	@GetMapping
	    public String redirect() {
	        return "redirect:/veiculos/listar";
	  }

	@GetMapping("/cadastrar")
	public String cadastrar(Veiculo veiculo) {
		return "veiculo/cadastro";
	}

	@GetMapping("/listar")
	public String listar(@RequestParam(required = false) String modelo, ModelMap model) {
		if(modelo == null || modelo.isBlank())
			model.addAttribute("veiculos", veiculoService.buscarTodos());
		else
			model.addAttribute("veiculos", veiculoService.buscarTodosPorModelo(modelo));

		return "veiculo/lista";
	}

	@GetMapping("/meus")
	@PreAuthorize("hasAuthority('LOJA')")
	public String listarVeiculosDaLoja(ModelMap model, @AuthenticationPrincipal UserDetails user) {
		Loja loja = lojaService.buscarPorEmail(user.getUsername());
		List<Veiculo> veiculos = veiculoService.buscarTodosPorLoja(loja);
		model.addAttribute("veiculos", veiculos);
		return "veiculo/listaLoja"; 
	}


	@PostMapping("/salvar")
	public String salvar(@Valid Veiculo veiculo, BindingResult result, @RequestParam(required = false) List<MultipartFile> arquivos, ModelMap model,  RedirectAttributes attr) {

		if (result.hasErrors()) {
			 System.out.println("Erros de validação encontrados: ");
			 result.getAllErrors().forEach(error -> System.out.println(error.toString()));

			return "veiculo/cadastro";
		}
		
	    List<Imagem> imagens = new ArrayList<>();
	    for (MultipartFile arquivo : arquivos) {
	        if (arquivo != null && !arquivo.isEmpty()) {
	            try {
	                Imagem img = new Imagem();
	                img.setNome(arquivo.getOriginalFilename());
	                img.setContentType(arquivo.getContentType());
	                img.setDados(arquivo.getBytes()); 
	                img.setVeiculo(veiculo);
	                imagens.add(img);
	            } catch (IOException e) {
	                model.addAttribute("fail", "Falha ao ler o arquivo " + arquivo.getOriginalFilename());
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
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("veiculo", veiculoService.buscarPorId(id));
		return "veiculo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Veiculo veiculo, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "veiculo/cadastro";
		}

		veiculoService.salvar(veiculo);
		attr.addFlashAttribute("sucess", "veiculo.edit.sucess");
		return "redirect:/veiculo/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Long id, RedirectAttributes attr) {
		veiculoService.excluir(id);
		attr.addFlashAttribute("sucess", "veiculo.delete.sucess");
		return "redirect:/veiculo/listar";
	}

	@ModelAttribute("lojas")
	public List<Loja> listaLojas() {
		return lojaService.buscarTodas();
	}
	
	@ModelAttribute("modelos")
	public List<String> listaModelos() {
		return veiculoService.buscarModelos();
	}

}
