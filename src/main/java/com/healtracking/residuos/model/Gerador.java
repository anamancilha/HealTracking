package com.healtracking.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gerador")
public class Gerador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerador")
    private Long idGerador;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "tipo_estabelecimento", nullable = false)
    private String tipoEstabelecimento; // Ex: Hospital, Clínica, Laboratório

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    // --- GETTERS E SETTERS ---
    public Long getIdGerador() { return idGerador; }
    public void setIdGerador(Long idGerador) { this.idGerador = idGerador; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getTipoEstabelecimento() { return tipoEstabelecimento; }
    public void setTipoEstablishment(String tipoEstabelecimento) { this.tipoEstabelecimento = tipoEstabelecimento; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}

