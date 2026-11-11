package com.healtracking.residuos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "O nome é obrigatório.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "Formato de e-mail inválido.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotBlank(message = "Selecione um perfil de acesso.")
    @Column(name = "perfil", nullable = false)
    private String perfil;

    @ManyToOne
    @JoinColumn(name = "id_gerador")
    private Gerador gerador;

    @ManyToOne
    @JoinColumn(name = "id_transportador")
    private Transportador transportador;

    @ManyToOne
    @JoinColumn(name = "id_tratamento")
    private Tratamento tratamento;

    // --- GETTERS E SETTERS ---
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
    public Gerador getGerador() { return gerador; }
    public void setGerador(Gerador gerador) { this.gerador = gerador; }
    public Transportador getTransportador() { return transportador; }
    public void setTransportador(Transportador transportador) { this.transportador = transportador; }
    public Tratamento getTratamento() { return tratamento; }
    public void setTratamento(Tratamento tratamento) { this.tratamento = tratamento; }
}
