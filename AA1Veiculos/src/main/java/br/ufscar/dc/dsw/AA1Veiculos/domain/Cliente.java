package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.ufscar.dc.dsw.AA1Veiculos.validation.UniqueCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends AbstractEntity<Long> {

    @UniqueCPF (message = "{Unique.CPF}")
	@NotBlank
	@Size(min = 14, max = 14, message = "{cliente.cpf.invalido}")
	@Column(nullable = false, unique = true, length = 14)
	private String cpf;

    @NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

    @OneToMany(mappedBy = "cliente")
    private List<Proposta> propostas;

    @NotBlank
    @Pattern(regexp = "\\d{10,15}")
    private String telefone;

    @NotBlank
    @Pattern(regexp = "M|F")
    private String sexo;

    @NotNull
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    public List<Proposta> getPropostas() {
        return propostas;
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

    public boolean temPropostasEmAberto() {
        if (propostas == null)
            return false;
        return propostas.stream().anyMatch(p -> p.isEmAberto());
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", unique = true, nullable = false)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
