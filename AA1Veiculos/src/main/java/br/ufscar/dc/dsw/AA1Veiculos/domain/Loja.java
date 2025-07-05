package br.ufscar.dc.dsw.AA1Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

import br.ufscar.dc.dsw.AA1Veiculos.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Loja")
public class Loja extends AbstractEntity<Long> {

    @UniqueCNPJ
    @NotBlank(message = "{cnpj.not.blank}")
    @Size(min = 18, max = 18, message = "{cnpj.size}")
    @Column(nullable = false, unique = true, length = 18)
    private String cnpj;

    @NotBlank(message = "{nome.not.blank}")
    @Size(min = 3, max = 255, message = "{nome.size}")
    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @Size(max = 500, message = "{descricao.size}")
    private String descricao;

    @OneToMany(mappedBy = "loja")
    private List<Veiculo> veiculos;

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
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
