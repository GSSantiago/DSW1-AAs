package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Size(max = 255)
    private String nome;

    @NotBlank
    @Pattern(regexp = "\\d{10,15}")
    private String telefone;

    @NotBlank
    @Pattern(regexp = "M|F")
    private String sexo;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
