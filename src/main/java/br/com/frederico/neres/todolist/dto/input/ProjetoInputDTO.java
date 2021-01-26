package br.com.frederico.neres.todolist.dto.input;

import br.com.frederico.neres.todolist.domain.model.Projeto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class ProjetoInputDTO {
    private String titulo;
    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Projeto buildProjeto() {
        return new Projeto.Builder()
                .titulo(titulo)
                .descricao(descricao)
                .build();
    }
}
