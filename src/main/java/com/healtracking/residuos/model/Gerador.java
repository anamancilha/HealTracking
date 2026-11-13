package com.healtracking.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "gerador")
public class Gerador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerador")
    private Long idGerador;

    @NotBlank(message = "A razão social é obrigatória.")
    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @NotBlank(message = "O tipo de estabelecimento é obrigatório.")
    @Column(name = "tipo_estabelecimento", nullable = false)
    private String tipoEstabelecimento;

    @NotBlank(message = "O CNPJ é obrigatório.")
    @CNPJ(message = "Informe um CNPJ válido.") // Calcula e valida o dígito verificador
    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    // Relacionamento com o Utilizador (para o isolamento por dono)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    // GETTERS E SETTERS ---
    public Long getIdGerador() { return idGerador; }
    public void setIdGerador(Long idGerador) { this.idGerador = idGerador; }
    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }
    public String getTipoEstabelecimento() { return tipoEstabelecimento; }
    public void setTipoEstabelecimento(String tipoEstabelecimento) { this.tipoEstabelecimento = tipoEstabelecimento; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}