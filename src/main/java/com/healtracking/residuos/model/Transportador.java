package com.healtracking.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "transportador")
public class Transportador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transportador")
    private Long idTransportador;

    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "placa_veiculo", nullable = false)
    private String placaVeiculo; // Super importante para rastreabilidade e fiscalização ambiental

    @Column(name = "numero_licenca", nullable = false)
    private String numeroLicenca; // Licença ambiental de operação (ex: CETESB, IBAMA)

    // --- GETTERS E SETTERS ---
    public Long getIdTransportador() { return idTransportador; }
    public void setIdTransportador(Long idTransportador) { this.idTransportador = idTransportador; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getPlacaVeiculo() { return placaVeiculo; }
    public void setPlacaVeiculo(String placaVeiculo) { this.placaVeiculo = placaVeiculo; }
    public String getNumeroLicenca() { return numeroLicenca; }
    public void setNumeroLicenca(String numeroLicenca) { this.numeroLicenca = numeroLicenca; }
}
