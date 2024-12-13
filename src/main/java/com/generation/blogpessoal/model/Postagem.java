package com.generation.blogpessoal.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo título é obrigatorio")
    @Size(min = 5, max = 100)
    private String titulo;

    @NotBlank(message = "O atributo texto é obrigatorio")
    @Size(min = 10, max = 1000)
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;

    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O atributo título é obrigatorio") @Size(min = 5, max = 100) String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "O atributo título é obrigatorio") @Size(min = 5, max = 100) String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "O atributo texto é obrigatorio") @Size(min = 10, max = 1000) String getTexto() {
        return texto;
    }

    public void setTexto(@NotBlank(message = "O atributo texto é obrigatorio") @Size(min = 10, max = 1000) String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
