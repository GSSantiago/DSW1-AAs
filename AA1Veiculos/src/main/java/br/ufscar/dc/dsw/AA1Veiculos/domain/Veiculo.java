package br.ufscar.dc.dsw.AA1Veiculos.domain;

import java.math.BigDecimal;

import br.ufscar.dc.dsw.AA1Veiculos.validation.UniqueChassi;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Veiculo")
public class Veiculo extends AbstractEntity<Long> {
    
	@NotNull(message = "{NotNull.veiculo.loja}")
	@ManyToOne
	@JoinColumn(name = "loja_id")
	private Loja loja;
	
	@NotBlank(message = "{NotBlank.veiculo.placa}")
	@Size(max = 7)
	@Column(nullable = false, length = 7)
	private String placa;
	
	@NotBlank(message = "{NotBlank.veiculo.modelo}")
	@Size(max = 20)
	@Column(nullable = false, length = 20)
	private String modelo;
	
	@NotBlank(message = "{NotBlank.veiculo.cnpj}")
	@Size(min = 14, max = 18, message = "{Size.editora.CNPJ}")
	@Column(nullable = false, length = 60)
	private String CNPJ;
	
	@UniqueChassi
	@NotBlank(message = "{NotBlank.veiculo.chassi}")
	@Column(nullable = false, unique = true, length = 17)
	private String chassi;
	
	@NotNull(message = "{NotNull.veiculo.ano}")
	@Column(nullable = false, length = 5)
	private Integer ano;
	
	@NotNull(message = "{NotNull.veiculo.quilometragem}")
	@Column(nullable = false, length = 12)
	private Integer quilometragem;
	
	@Column(nullable = false, length = 500)
	private String descricao;
	
	@NotNull (message = "{NotNull.veiculo.valor}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal valor;
	
	//[TODO] Adicionar fotos do veiculo
	
	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
	    this.loja = loja;
	    if (loja != null) {
	        this.CNPJ = loja.getCnpj();
	    }
	}
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
}

