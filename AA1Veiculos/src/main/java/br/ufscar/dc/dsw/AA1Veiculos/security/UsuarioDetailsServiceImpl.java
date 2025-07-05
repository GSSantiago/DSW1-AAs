package br.ufscar.dc.dsw.AA1Veiculos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw.AA1Veiculos.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Usuario;

public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO dao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Usuario usuario = dao.findByEmail(email);

        if (usuario == null) {
            String msg = messageSource.getMessage(
                "usuario.nao.encontrado", null, LocaleContextHolder.getLocale());
            throw new UsernameNotFoundException(msg);
        }

        return new UsuarioDetails(usuario);
    }
}
