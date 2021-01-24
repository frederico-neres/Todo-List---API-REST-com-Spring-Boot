package br.com.frederico.neres.todolist.domain.repository;

import br.com.frederico.neres.todolist.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByDescricao(String titulo);

}
