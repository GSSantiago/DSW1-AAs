package br.ufscar.dc.dsw.AA2Veiculos.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Proposta", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "cliente_id", "veiculo_id", "status" })
})
public class Proposta extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @NotNull
    @Column(nullable = false)
    private LocalDate dataProposta;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "{proposta.valor.positivo}")
    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    private BigDecimal valorProposta;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String condicoesPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private StatusProposta status;

    @Size(max = 500)
    private String contraProposta;

    @Size(max = 500)
    private String linkReuniao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime horarioReuniao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(LocalDate dataProposta) {
        this.dataProposta = dataProposta;
    }

    public BigDecimal getValorProposta() {
        return valorProposta;
    }

    public void setValorProposta(BigDecimal valorProposta) {
        this.valorProposta = valorProposta;
    }

    public String getCondicoesPagamento() {
        return condicoesPagamento;
    }

    public void setCondicoesPagamento(String condicoesPagamento) {
        this.condicoesPagamento = condicoesPagamento;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public String getContraProposta() {
        return contraProposta;
    }

    public void setContraProposta(String contraProposta) {
        this.contraProposta = contraProposta;
    }

    public String getLinkReuniao() {
        return linkReuniao;
    }

    public void setLinkReuniao(String linkReuniao) {
        this.linkReuniao = linkReuniao;
    }

    public LocalDateTime getHorarioReuniao(){
        return horarioReuniao;
    }

    public void setHorarioReuniao(LocalDateTime horarioReuniao){
        this.horarioReuniao = horarioReuniao;
    }
}

