package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import br.ufscar.dc.dsw.AA1Veiculos.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Loja")
public class Loja extends Usuario {

    @UniqueCNPJ
    @NotBlank(message = "{cnpj.not.blank}")
    @Size(min = 14, max = 18, message = "{cnpj.size}")
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @NotBlank(message = "{nome.not.blank}")
    @Size(max = 255, message = "{nome.size}")
    @Column(nullable = false)
    private String nome;

    @Size(max = 500, message = "{descricao.size}")
    private String descricao;

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
