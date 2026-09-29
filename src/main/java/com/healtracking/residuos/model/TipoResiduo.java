package com.healtracking.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_residuo")
public class TipoResiduo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_residuo")
    private Long idTipoResiduo;

    @Column(name = "nome_tipo", nullable = false)
    private String nomeTipo;

    @Column(name = "grupo_classificacao", nullable = false)
    private String grupoClassificacao;

    //Métodos GET e SET

    public Long getIdTipoResiduo() {
        return idTipoResiduo;
    }
    public void setIdTipoResiduo(Long idTipoResiduo) {
        this.idTipoResiduo = idTipoResiduo;
    }
    public String getNomeTipo() {
        return nomeTipo;
    }
    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    public String getGrupoClassificacao() {
        return grupoClassificacao;
    }
    public void setGrupoClassificacao(String grupoClassificacao) {
        this.grupoClassificacao = grupoClassificacao;
    }
}