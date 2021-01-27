package br.com.frederico.neres.todolist.dto.input;

import br.com.frederico.neres.todolist.domain.model.Tarefa;

import javax.validation.constraints.NotBlank;

public class TarefaInputDTO {

    @NotBlank(message = "Título não pode ser vazio!")
    private String titulo;

    @NotBlank(message = "Descrição não pode ser vazio!")
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Tarefa buildTarefa() {
        return new Tarefa.Builder()
                .titulo(titulo)
                .descricao(descricao)
                .build();
    }
}
