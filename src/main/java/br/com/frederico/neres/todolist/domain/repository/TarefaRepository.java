package br.com.frederico.neres.todolist.domain.repository;

import br.com.frederico.neres.todolist.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
