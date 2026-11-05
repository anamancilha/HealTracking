package com.healtracking.residuos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "perfil", nullable = false)
    private String perfil; // Valores: ADMIN, GERADOR, TRANSPORTADOR, TRATAMENTO

    // OPCIONAL: Vinculações diretas para o sistema saber a qual empresa esse usuário pertence
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
