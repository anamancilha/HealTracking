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

    @ManyToOne
    @JoinColumn(name = "id_residuo", nullable = false)
    private Residuo residuo;

    @ManyToOne
    @JoinColumn(name = "id_transportador", nullable = false)
    private Transportador transportador;

    @ManyToOne
    @JoinColumn(name = "id_tratamento", nullable = false)
    private Tratamento tratamento;

    // Adicionado para vincular a coleta ao utilizador logado
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

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

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}