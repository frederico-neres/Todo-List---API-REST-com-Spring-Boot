package br.com.frederico.neres.todolist.controller;

import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {


    public ProjetoRepository projetoRepository;

    public ProjetoController(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @GetMapping("/tarefas/{idProjeto}")
    public List<Tarefa> buscarTarefas(@PathVariable(value="idProjeto") long idProjeto) {
        Projeto projeto = projetoRepository.findById(idProjeto).orElseThrow(()-> new RuntimeException("Projeto Não encontrado!"));
        return projeto.getTarefas();
    }

    @PostMapping
    public Projeto salvar(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }
}
