package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {

    @NotBlank
    @Size(max = 14)
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String telefone;

    @NotBlank
    @Pattern(regexp = "M|F")
    private String sexo;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private java.util.Date nascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public java.util.Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(java.util.Date nascimento) {
        this.nascimento = nascimento;
    }
}
