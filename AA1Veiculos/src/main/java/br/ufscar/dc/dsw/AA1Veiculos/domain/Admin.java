package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "Admin")
public class Admin extends Usuario {

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
