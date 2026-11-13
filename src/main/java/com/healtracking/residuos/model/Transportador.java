package com.healtracking.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "transportador")
public class Transportador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportador")
    private Long idTransportador;

    @NotBlank(message = "O nome do representante é obrigatório.")
    @Column(name = "nome_representante", nullable = false)
    private String nomeRepresentante;

    @NotBlank(message = "O nome da empresa é obrigatório.")
    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @NotBlank(message = "O número de licença é obrigatório.")
    @Column(name = "numero_licenca", nullable = false)
    private String numeroLicenca;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "telefone")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // --- GETTERS E SETTERS ---
    public Long getIdTransportador() { return idTransportador; }
    public void setIdTransportador(Long idTransportador) { this.idTransportador = idTransportador; }

    public String getNomeRepresentante() { return nomeRepresentante; }
    public void setNomeRepresentante(String nomeRepresentante) { this.nomeRepresentante = nomeRepresentante; }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    public String getNumeroLicenca() { return numeroLicenca; }
    public void setNumeroLicenca(String numeroLicenca) { this.numeroLicenca = numeroLicenca; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}