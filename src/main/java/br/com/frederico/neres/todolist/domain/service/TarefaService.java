package br.com.frederico.neres.todolist.domain.service;

import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.TarefaRepository;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {
    TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
}
