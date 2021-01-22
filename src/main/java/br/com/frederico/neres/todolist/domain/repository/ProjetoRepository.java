package br.com.frederico.neres.todolist.domain.repository;

import br.com.frederico.neres.todolist.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
