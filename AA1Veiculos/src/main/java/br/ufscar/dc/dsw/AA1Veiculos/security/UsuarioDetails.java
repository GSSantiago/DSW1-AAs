package br.ufscar.dc.dsw.AA1Veiculos.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

public class UsuarioDetails implements UserDetails {

    private Cliente cliente;
    private Loja loja;
    private String role;

    public UsuarioDetails(Cliente cliente) {
        this.cliente = cliente;
        if (cliente.getEmail().equalsIgnoreCase("admin@admin.com")) {
            this.role = "ROLE_ADMIN";
        } else {
            this.role = "ROLE_CLIENTE";
        }
    }

    public UsuarioDetails(Loja loja) {
        this.loja = loja;
        this.role = "ROLE_LOJA";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return cliente != null ? cliente.getSenha() : loja.getSenha();
    }

    @Override
    public String getUsername() {
        return cliente != null ? cliente.getEmail() : loja.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Loja getLoja() {
        return loja;
    }
}
