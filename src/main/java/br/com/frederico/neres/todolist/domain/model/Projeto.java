package br.com.frederico.neres.todolist.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
public class Projeto {

    public Projeto() {
    }

    private Projeto(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título não pode ser vazio!")
    @Column(length = 60, nullable = false, unique = true)
    private String titulo;

    @NotBlank(message = "Descrição não pode ser vazio")
    @Column(nullable = false, unique = true)
    private String descricao;

    @OneToMany(mappedBy = "projeto")
    @JsonManagedReference
    private List<Tarefa> tarefas;


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

        public Projeto build()
        {
           return new Projeto(titulo, descricao);
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return id.equals(projeto.id) && titulo.equals(projeto.titulo) && descricao.equals(projeto.descricao) && tarefas.equals(projeto.tarefas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, tarefas);
    }
}
