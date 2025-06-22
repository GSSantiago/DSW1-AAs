package br.ufscar.dc.dsw.AA1Veiculos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IAdminDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.IClienteDAO;
import br.ufscar.dc.dsw.AA1Veiculos.dao.ILojaDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Admin;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IClienteDAO clienteDAO;

    @Autowired
    private ILojaDAO lojaDAO;

    @Autowired
    private IAdminDAO adminDAO;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminDAO.findByEmail(email);
        Cliente cliente = clienteDAO.findByEmail(email);
        Loja loja = lojaDAO.findByEmail(email);

        if (admin != null) {
            return new UsuarioDetails(admin, "ROLE_ADMIN");
        }
        if (cliente != null) {
            return new UsuarioDetails(cliente, "ROLE_CLIENTE");
        }
        if (loja != null) {
            return new UsuarioDetails(loja, "ROLE_LOJA");
        }
        throw new UsernameNotFoundException(
                messageSource.getMessage("usuario.nao.encontrado", new Object[] { email },
                        LocaleContextHolder.getLocale()));
    }



}
