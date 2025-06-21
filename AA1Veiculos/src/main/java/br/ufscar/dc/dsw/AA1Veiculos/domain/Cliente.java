package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{cliente.email.notblank}")
    @Email(message = "{cliente.email.invalido}")
    @Size(max = 255, message = "{cliente.email.tamanho}")
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank(message = "{cliente.senha.notblank}")
    @Size(min = 6, max = 64, message = "{cliente.senha.tamanho}")
    @Column(nullable = false, length = 64)
    private String senha;

    @NotBlank(message = "{cliente.cpf.notblank}")
    @Pattern(regexp = "\\d{11}", message = "{cliente.cpf.invalido}")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank(message = "{cliente.nome.notblank}")
    @Size(max = 255, message = "{cliente.nome.tamanho}")
    @Column(nullable = false, length = 255)
    private String nome;

    @NotBlank(message = "{cliente.telefone.notblank}")
    @Pattern(regexp = "\\d{10,15}", message = "{cliente.telefone.invalido}")
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotBlank(message = "{cliente.sexo.notblank}")
    @Pattern(regexp = "M|F", message = "{cliente.sexo.invalido}")
    @Column(nullable = false, length = 1)
    private String sexo;

    @NotNull(message = "{cliente.dataNascimento.notnull}")
    @Past(message = "{cliente.dataNascimento.passado}")
    @Column(nullable = false)
    private LocalDate dataNascimento;


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
