package br.com.frederico.neres.todolist.controller;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import br.com.frederico.neres.todolist.domain.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    public ProjetoService projetoService;


    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public List<Projeto> buscarTodos() {
        return null;
    }

    @GetMapping("/tarefas/{idProjeto}")
    public ResponseEntity<Response<List<Tarefa>>> buscarTarefas(@PathVariable(value="idProjeto") long idProjeto) {
        System.out.println();
        return projetoService.buscarTarefas(idProjeto);
    }

    @PostMapping
    public Projeto salvar(@RequestBody Projeto projeto) {
        return projetoService.salvar(projeto);
    }
}
