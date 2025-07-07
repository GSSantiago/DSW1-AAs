package br.ufscar.dc.dsw.AA1Veiculos.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@SuppressWarnings("serial")
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {

    @NotBlank(message = "{cpf.not.blank}")
    @Size(min = 14, max = 14, message = "{cpf.size}")
    @Column(nullable = false, unique = true)
    private String cpf;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String telefone;

    @NotBlank
    @Pattern(regexp = "M|F")
    private String sexo;

    @NotNull(message = "{cliente.nascimento.notnull}")
    @Past(message = "{cliente.nascimento.past}")
    @Column(nullable = false)
    private LocalDate nascimento;
    

    public java.time.LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(java.time.LocalDate nascimento) {
        this.nascimento = nascimento;
    }

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
}
