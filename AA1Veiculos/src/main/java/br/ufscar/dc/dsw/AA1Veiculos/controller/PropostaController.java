package br.ufscar.dc.dsw.AA1Veiculos.controller;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IPropostaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.*;
import br.ufscar.dc.dsw.AA1Veiculos.security.UsuarioDetails;
//import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IEmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private IPropostaDAO propostaDAO;

    @Autowired
    private IVeiculoDAO veiculoDAO;

    /*@Autowired
    private IEmailService emailService;*/


    @GetMapping("/formulario/{veiculoId}")
    @PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public String criarProposta(@PathVariable Long veiculoId, Model model) {
        Veiculo veiculo = veiculoDAO.findById(veiculoId)
                                    .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado com ID: " + veiculoId));
        Proposta proposta = new Proposta();
        proposta.setVeiculo(veiculo); 

        model.addAttribute("proposta", proposta);
        model.addAttribute("veiculo", veiculo); 

        return "propostas/formulario";
    }


    @PostMapping("/salvar")
    @PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public String salvarProposta(@Valid @ModelAttribute("proposta") Proposta proposta,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes ra) {

        Veiculo veiculo = veiculoDAO.findById(proposta.getVeiculo().getId())
                                    .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        proposta.setVeiculo(veiculo);

        if (result.hasErrors()) {
            model.addAttribute("veiculo", veiculo); 
            return "propostas/formulario";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

        if (!(usuarioDetails.getUsuario() instanceof Cliente)) {
            ra.addFlashAttribute("erro", "Erro: Usuário autenticado não é um cliente.");
            return "redirect:/logout"; 
        }
        Cliente cliente = (Cliente) usuarioDetails.getUsuario();
        proposta.setCliente(cliente);

        boolean existeAberta = propostaDAO.existsByClienteAndVeiculoAndStatus(cliente, proposta.getVeiculo(), StatusProposta.ABERTO);
        if (existeAberta) {
            model.addAttribute("erro", "Você já tem uma proposta em aberto para este veículo.");
            model.addAttribute("veiculo", veiculo); 
            return "propostas/formulario";
        }

        proposta.setDataProposta(LocalDate.now());
        proposta.setStatus(StatusProposta.ABERTO); 

        propostaDAO.save(proposta);
        ra.addFlashAttribute("mensagem", "Proposta enviada com sucesso!");

        return "redirect:/propostas/listaCliente";
    }


    @GetMapping("/listaCliente")
    @PreAuthorize("hasAuthority('ROLE_CLIENTE')")
    public String listarPropostasCliente(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

        if (!(usuarioDetails.getUsuario() instanceof Cliente)) {
            model.addAttribute("erro", "Erro: Usuário autenticado não é um cliente.");
            return "error/accessDenied"; 
        }
        Cliente cliente = (Cliente) usuarioDetails.getUsuario();

        List<Proposta> propostas = propostaDAO.findAllByCliente(cliente);
        model.addAttribute("propostas", propostas);

        return "propostas/listaCliente";
    }


    @GetMapping("/listaLoja")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String listarPropostasLoja(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();

        if (!(usuarioDetails.getUsuario() instanceof Loja)) {
            model.addAttribute("erro", "Erro: Usuário autenticado não é uma loja.");
            return "error/accessDenied"; 
        }
        Loja loja = (Loja) usuarioDetails.getUsuario();

        List<Proposta> propostas = propostaDAO.findAllByVeiculoLoja(loja);
        model.addAttribute("propostas", propostas);

        return "propostas/listaLoja";
    }


    @GetMapping("/aceitar/{id}")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String aceitarPropostaForm(@PathVariable Long id, Model model) {
        Proposta proposta = propostaDAO.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada com ID: " + id));
        model.addAttribute("proposta", proposta);
        return "propostas/aceitar";
    }


    @PostMapping("/aceitar")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String aceitarProposta(@RequestParam Long id,
                                  @RequestParam String linkReuniao,
                                  @RequestParam String horario,
                                  RedirectAttributes ra) {
        Proposta proposta = propostaDAO.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        Loja loja = (Loja) usuarioDetails.getUsuario();

        if (!proposta.getVeiculo().getLoja().equals(loja)) {
            ra.addFlashAttribute("erro", "Você não tem permissão para aceitar esta proposta.");
            return "redirect:/propostas/listaLoja";
        }


        proposta.setStatus(StatusProposta.ACEITO);
        proposta.setLinkReuniao(linkReuniao);

        propostaDAO.save(proposta);

        ra.addFlashAttribute("mensagem", "Proposta aceita com sucesso!");

        // String mensagemEmail = "Sua proposta para o veículo " + proposta.getVeiculo().getModelo() + " foi aceita!\n";
        // mensagemEmail += "Detalhes da reunião: " + horario + "\n";
        // mensagemEmail += "Link da reunião: " + linkReuniao;
        // emailService.enviarEmail(proposta.getCliente().getEmail(), "Proposta Aceita", mensagemEmail);

        return "redirect:/propostas/listaLoja";
    }


    @GetMapping("/recusar/{id}")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String recusarPropostaForm(@PathVariable Long id, Model model) {
        Proposta proposta = propostaDAO.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada com ID: " + id));
        model.addAttribute("proposta", proposta);
        return "propostas/recusar";
    }

    @PostMapping("/recusar")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String recusarProposta(@RequestParam Long id,
                                  @RequestParam(required = false) String contraProposta,
                                  RedirectAttributes ra) {
        Proposta proposta = propostaDAO.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));

        // Basic check to ensure the proposal belongs to the authenticated store's vehicle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        Loja loja = (Loja) usuarioDetails.getUsuario();

        if (!proposta.getVeiculo().getLoja().equals(loja)) {
            ra.addFlashAttribute("erro", "Você não tem permissão para recusar esta proposta.");
            return "redirect:/propostas/listaLoja";
        }


        proposta.setStatus(StatusProposta.NAO_ACEITO);
        proposta.setContraProposta(contraProposta);
        propostaDAO.save(proposta);

        ra.addFlashAttribute("mensagem", "Proposta recusada com sucesso!");

        // String mensagemEmail = "Sua proposta para o veículo " + proposta.getVeiculo().getModelo() + " foi recusada.";
        // if (contraProposta != null && !contraProposta.isBlank()) {
        //     mensagemEmail += "\nContra proposta da loja: " + contraProposta;
        // }
        // emailService.enviarEmail(proposta.getCliente().getEmail(), "Proposta Recusada", mensagemEmail);

        return "redirect:/propostas/listaLoja";
    }
}