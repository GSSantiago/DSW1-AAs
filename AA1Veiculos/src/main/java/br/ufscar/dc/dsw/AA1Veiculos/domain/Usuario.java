package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Usuario extends AbstractEntity<Long> {

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, unique = true, length = 255)
    protected String email;

    @NotBlank
    @Size(min = 4, max = 255)
    @Column(nullable = false, length = 255)
    protected String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
