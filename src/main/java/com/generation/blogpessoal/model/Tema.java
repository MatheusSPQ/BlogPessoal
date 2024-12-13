package com.generation.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_temas")
public class Tema {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank(message = "O atributo descricao é obrigatorio")
    @Size(min = 10, max = 1000)
    private String descricao;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tema", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties
    private List<Postagem> postagem;

    public @NotBlank(message = "O atributo descricao é obrigatorio") @Size(min = 10, max = 1000) String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotBlank(message = "O atributo descricao é obrigatorio") @Size(min = 10, max = 1000) String descricao) {
        this.descricao = descricao;
    }


    public List<Postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<Postagem> postagem) {
        this.postagem = postagem;
    }
}
