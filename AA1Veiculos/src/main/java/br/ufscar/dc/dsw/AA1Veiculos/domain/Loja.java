package br.ufscar.dc.dsw.AA1Veiculos.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import br.ufscar.dc.dsw.AA1Veiculos.validation.UniqueCNPJ;

@Entity
@Table(name = "Loja")
public class Loja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(min = 6, max = 64)
    private String senha;

    @UniqueCNPJ
    @NotBlank
    @Size(min = 14, max = 18) 
    private String cnpj;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @Size(max = 500)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
