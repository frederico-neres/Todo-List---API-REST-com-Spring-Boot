package br.com.frederico.neres.todolist.domain.repository;

import br.com.frederico.neres.todolist.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
