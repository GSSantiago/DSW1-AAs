package br.ufscar.dc.dsw.AA1Veiculos.controller;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IPropostaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IVeiculoDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.*;
import br.ufscar.dc.dsw.AA1Veiculos.security.UsuarioDetails;
import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IEmailService;
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

    @Autowired
    private IEmailService emailService;


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


    @GetMapping("/listaPropostaLoja")
    @PreAuthorize("hasAuthority('ROLE_LOJA')")
    public String listarPropostasLoja(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();


        if (!(usuarioDetails.getUsuario() instanceof Loja)) {
            model.addAttribute("erro", "Erro: Usuário autenticado não é uma loja.");
            return "error/accessDenied"; 
        }
        
        Loja loja = (Loja) usuarioDetails.getUsuario();

        List<Proposta> propostas = propostaDAO.findAllByVeiculoLojaAndStatus(loja, StatusProposta.ABERTO);
        model.addAttribute("propostas", propostas);

        return "propostas/listaPropostaLoja";
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
    public String aceitarProposta(@ModelAttribute("proposta") Proposta proposta,
                                RedirectAttributes ra) {
        Proposta propostaExistente = propostaDAO.findById(proposta.getId())
                .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));


        propostaExistente.setStatus(StatusProposta.ACEITO);
        propostaExistente.setLinkReuniao(proposta.getLinkReuniao());
        propostaExistente.setHorarioReuniao(proposta.getHorarioReuniao());
        propostaDAO.save(propostaExistente);


        List<Proposta> outrasPropostas = propostaDAO.findAllByVeiculoAndStatus(propostaExistente.getVeiculo(), StatusProposta.ABERTO);
        for (Proposta p : outrasPropostas) {
            if (!p.getId().equals(propostaExistente.getId())) {
                p.setStatus(StatusProposta.NAO_ACEITO);
                propostaDAO.save(p);

                String emailRecusa = "Sua proposta para o veículo " + p.getVeiculo().getModelo() + " foi recusada automaticamente porque outra proposta foi aceita.";
                emailService.enviarEmail(p.getCliente().getEmail(), "Proposta Recusada", emailRecusa);
            }
        }


        ra.addFlashAttribute("mensagem", "Proposta aceita com sucesso!");

        String mensagemEmail = "Sua proposta para o veículo " + propostaExistente.getVeiculo().getModelo() + " foi aceita!\n";
        mensagemEmail += "Detalhes da reunião: " + propostaExistente.getHorarioReuniao() + "\n";
        mensagemEmail += "Link da reunião: " + propostaExistente.getLinkReuniao();
        emailService.enviarEmail(propostaExistente.getCliente().getEmail(), "Proposta Aceita", mensagemEmail);

        return "redirect:/propostas/listaPropostaLoja";
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
    public String recusarProposta(@ModelAttribute("proposta") Proposta propostaAtualizada,
                                  RedirectAttributes ra) {
        Proposta proposta = propostaDAO.findById(propostaAtualizada.getId())
                                     .orElseThrow(() -> new IllegalArgumentException("Proposta não encontrada."));

       

        proposta.setStatus(StatusProposta.NAO_ACEITO);
        
        proposta.setValorProposta(propostaAtualizada.getValorProposta()); 
        proposta.setCondicoesPagamento(propostaAtualizada.getCondicoesPagamento()); 
        proposta.setContraProposta(propostaAtualizada.getContraProposta()); 

        propostaDAO.save(proposta);

        ra.addFlashAttribute("mensagem", "Proposta recusada com sucesso!");

        String mensagemEmail = "Sua proposta para o veículo " + proposta.getVeiculo().getModelo() + " foi recusada.";
        if (proposta.getContraProposta() != null && !proposta.getContraProposta().isBlank()) {
            mensagemEmail += "\nContra proposta da loja: " + proposta.getContraProposta();
        }

        if (proposta.getValorProposta() != null) {
            mensagemEmail += "\nValor da contra proposta: " + proposta.getValorProposta();
        }
        if (proposta.getCondicoesPagamento() != null && !proposta.getCondicoesPagamento().isBlank()) {
            mensagemEmail += "\nCondições de pagamento: " + proposta.getCondicoesPagamento();
        }

        emailService.enviarEmail(proposta.getCliente().getEmail(), "Proposta Recusada", mensagemEmail);

        return "redirect:/propostas/listaPropostaLoja";
    }
}