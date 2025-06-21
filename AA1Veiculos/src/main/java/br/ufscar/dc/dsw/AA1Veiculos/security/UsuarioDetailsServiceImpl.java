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

    private static final String ADMIN_EMAIL = "admin@admin.com";
    private static final String ADMIN_SENHA = "$2a$10$H/F92qlL0UebZC4GribOlOv1Ut9ZY3W7hJ9mnSlLGK5soK.2nNHCS"; 
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
            return User.builder()
                    .username(ADMIN_EMAIL)
                    .password(ADMIN_SENHA)
                    .roles("ADMIN")
                    .build();
        }

        Cliente cliente = clienteDAO.findByEmail(email);
        if (cliente != null) {
            return new UsuarioDetails(cliente);
        }

        Loja loja = lojaDAO.findByEmail(email);
        if (loja != null) {
            return new UsuarioDetails(loja);
        }

        throw new UsernameNotFoundException("Usuário com e-mail " + email + " não encontrado.");
    }
}
