package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_user")
public class Usuario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Atributo Nome é Obrigatório!")
    private String nome;

    @NotNull(message = "O Atributo Usuário é Obrigatório!")
    @Email(message = "O Atributo Usuário deve ser um email válido!")
    private String usuario;

    @NotBlank(message = "O Atributo Senha é Obrigatório!")
    @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
    private String senha;

    @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Postagem> postagem;

    public Usuario(Long id, String nome, String usuario, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
    }

    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O Atributo Nome é Obrigatório!") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "O Atributo Nome é Obrigatório!") String nome) {
        this.nome = nome;
    }

    public @NotNull(message = "O Atributo Usuário é Obrigatório!") @Email(message = "O Atributo Usuário deve ser um email válido!") String getUsuario() {
        return usuario;
    }

    public void setUsuario(@NotNull(message = "O Atributo Usuário é Obrigatório!") @Email(message = "O Atributo Usuário deve ser um email válido!") String usuario) {
        this.usuario = usuario;
    }

    public @NotBlank(message = "O Atributo Senha é Obrigatório!") @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank(message = "O Atributo Senha é Obrigatório!") @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres") String senha) {
        this.senha = senha;
    }

    public @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres") String getFoto() {
        return foto;
    }

    public void setFoto(@Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres") String foto) {
        this.foto = foto;
    }

    public List<Postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<Postagem> postagem) {
        this.postagem = postagem;
    }
}
