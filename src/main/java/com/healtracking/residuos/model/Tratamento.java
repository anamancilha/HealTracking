package com.healtracking.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tratamento")
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tratamento")
    private Long idTratamento;

    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "tipo_tratamento", nullable = false)
    private String tipoTratamento; // Ex: Incineração, Autoclavagem, Coprocessamento, Aterro Classe I

    @Column(name = "endereco", nullable = false)
    private String endereco;

    // --- GETTERS E SETTERS ---
    public Long getIdTratamento() { return idTratamento; }
    public void setIdTratamento(Long idTratamento) { this.idTratamento = idTratamento; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getTipoTratamento() { return tipoTratamento; }
    public void setTipoTratamento(String tipoTratamento) { this.tipoTratamento = tipoTratamento; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}
