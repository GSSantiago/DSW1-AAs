package br.ufscar.dc.dsw.AA2Veiculos.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Imagem")
public class Imagem extends AbstractEntity<Long>  {

    @ManyToOne(optional = false)
    @JoinColumn(name = "veiculo_id")
    @JsonBackReference
    private Veiculo veiculo;

	@Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 100)
    private String contentType;

    @JsonIgnore
    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] dados;


    public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getDados() {
		return dados;
	}

	public void setDados(byte[] dados) {
		this.dados = dados;
	}
}
