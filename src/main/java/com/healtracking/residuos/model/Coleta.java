package com.healtracking.residuos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "coleta")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coleta")
    private Long idColeta;

    @Column(name = "data_coleta", nullable = false)
    private LocalDate dataColeta;

    // RELACIONAMENTO: Muitas coletas podem levar um Residuo (1 por formulário neste modelo simples)
    @ManyToOne
    @JoinColumn(name = "id_residuo", nullable = false)
    private Residuo residuo;

    // RELACIONAMENTO: Muitas coletas são feitas por um Transportador
    @ManyToOne
    @JoinColumn(name = "id_transportador", nullable = false)
    private Transportador transportador;

    // RELACIONAMENTO: Muitas coletas entregam em um local de Tratamento
    @ManyToOne
    @JoinColumn(name = "id_tratamento", nullable = false)
    private Tratamento tratamento;

    // --- GETTERS E SETTERS ---
    public Long getIdColeta() { return idColeta; }
    public void setIdColeta(Long idColeta) { this.idColeta = idColeta; }
    public LocalDate getDataColeta() { return dataColeta; }
    public void setDataColeta(LocalDate dataColeta) { this.dataColeta = dataColeta; }
    public Residuo getResiduo() { return residuo; }
    public void setResiduo(Residuo residuo) { this.residuo = residuo; }
    public Transportador getTransportador() { return transportador; }
    public void setTransportador(Transportador transportador) { this.transportador = transportador; }
    public Tratamento getTratamento() { return tratamento; }
    public void setTratamento(Tratamento tratamento) { this.tratamento = tratamento; }
}

