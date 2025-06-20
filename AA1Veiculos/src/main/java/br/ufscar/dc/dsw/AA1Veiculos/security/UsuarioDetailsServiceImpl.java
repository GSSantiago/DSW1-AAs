package br.ufscar.dc.dsw.AA1Veiculos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IClienteDAO clienteDAO;

    @Autowired
    private ILojaDAO lojaDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteDAO.findByEmail(email);
        if (cliente != null) {
            return new UsuarioDetails(cliente);
        }

        Loja loja = lojaDAO.findByEmail(email);
        if (loja != null) {
            return new UsuarioDetails(loja);
        }

        //pode adicionar verificação para admin fixo
        throw new UsernameNotFoundException("Usuário com e-mail " + email + " não encontrado.");
    }
}
