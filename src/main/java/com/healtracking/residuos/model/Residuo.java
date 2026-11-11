package com.healtracking.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Table(name = "residuo")
public class Residuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_residuo")
    private Long idResiduo;

    @NotNull(message = "O peso é obrigatório.")
    @Positive(message = "O peso deve ser maior que zero.")
    @Column(name = "peso_kg", nullable = false)
    private Double pesoKg;

    @NotNull(message = "A data de geração é obrigatória.")
    @Column(name = "data_geracao", nullable = false)
    private LocalDate dataGeracao;

    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Selecione um estabelecimento gerador.")
    @ManyToOne
    @JoinColumn(name = "id_gerador", nullable = false)
    private Gerador gerador;

    @NotNull(message = "Selecione o tipo de resíduo.")
    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo", nullable = false)
    private TipoResiduo tipoResiduo;

    // GETTERS E SETTERS
    public Long getIdResiduo() { return idResiduo; }
    public void setIdResiduo(Long idResiduo) { this.idResiduo = idResiduo; }
    public Double getPesoKg() { return pesoKg; }
    public void setPesoKg(Double pesoKg) { this.pesoKg = pesoKg; }
    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Gerador getGerador() { return gerador; }
    public void setGerador(Gerador gerador) { this.gerador = gerador; }
    public TipoResiduo getTipoResiduo() { return tipoResiduo; }
    public void setTipoResiduo(TipoResiduo tipoResiduo) { this.tipoResiduo = tipoResiduo; }
}

