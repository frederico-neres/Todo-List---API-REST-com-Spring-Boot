package br.com.frederico.neres.todolist.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 60, nullable = false, unique = true)
    private String titulo;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefaEnum status;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    @JsonBackReference
    private Projeto projeto;


    public Tarefa() {
    }

    private Tarefa(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public static class Builder {
        private String titulo;
        private String descricao;

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Tarefa build() {
            return new Tarefa(this.titulo, this.descricao);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTarefaEnum getStatus() {
        return status;
    }

    public void setStatus(StatusTarefaEnum status) {
        this.status = status;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id) && Objects.equals(titulo, tarefa.titulo) && Objects.equals(descricao, tarefa.descricao) && status == tarefa.status && Objects.equals(projeto, tarefa.projeto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, status, projeto);
    }
}

