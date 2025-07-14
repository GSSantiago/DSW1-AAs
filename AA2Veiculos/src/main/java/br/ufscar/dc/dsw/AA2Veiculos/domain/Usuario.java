package br.ufscar.dc.dsw.AA2Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import br.ufscar.dc.dsw.AA2Veiculos.validation.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank (message = "{nome.not.blank}")
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(groups = ValidationGroups.OnCreate.class, message = "{usuario.senha.notblank}")
    @Size(min = 6, message = "{usuario.senha.size}")
    @Column(nullable = false, length = 64)
    private String senha;

    @NotBlank
    @Column(nullable = false, length = 15)
    private String papel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public String getRole() {
        return "ROLE_" + papel.toUpperCase();
    }
}
