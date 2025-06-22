package br.ufscar.dc.dsw.AA1Veiculos.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Cliente;
import br.ufscar.dc.dsw.AA1Veiculos.domain.Loja;

public class UsuarioDetails implements UserDetails {

    private Object usuario; 
    private String role;
    private String username; 
    private String password; 

    public UsuarioDetails(Cliente cliente) {
        this.usuario = cliente;
        this.username = cliente.getEmail();
        this.password = cliente.getSenha();
        if (cliente.getEmail().equalsIgnoreCase("admin@admin.com")) {
            this.role = "ROLE_ADMIN";
        } else {
            this.role = "ROLE_CLIENTE";
        }
    }

  
    public UsuarioDetails(Loja loja) {
        this.usuario = loja;
        this.username = loja.getEmail();
        this.password = loja.getSenha();
        this.role = "ROLE_LOJA";
    }

    // Contrutor Admin
    public UsuarioDetails(String username, String password, String role) {
        this.usuario = null; 
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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


    public Object getUsuario() {
        return usuario;
    }

    public String getRole() {
        return role;
    }
}