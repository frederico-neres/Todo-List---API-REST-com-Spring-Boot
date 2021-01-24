package br.com.frederico.neres.todolist.controller;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import br.com.frederico.neres.todolist.domain.service.ProjetoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    public ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public ResponseEntity buscarTodos() {
        return projetoService.buscarTodos();
    }

    @GetMapping("/tarefas/{idProjeto}")
    public ResponseEntity buscarTarefas(@PathVariable(value="idProjeto") long idProjeto) {
        return projetoService.buscarTarefas(idProjeto);
    }

    @PostMapping
    public ResponseEntity salvar(@Valid @RequestBody Projeto projeto, BindingResult result) {
        return projetoService.salvar(projeto, result);
    }
}
