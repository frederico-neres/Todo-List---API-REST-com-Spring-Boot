package br.com.frederico.neres.todolist.controller;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.StatusTarefaEnum;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.service.ProjetoService;
import br.com.frederico.neres.todolist.domain.service.TarefaService;
import br.com.frederico.neres.todolist.dto.input.TarefaInputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    TarefaService tarefaService;
    ProjetoService projetoService;

    public TarefaController(TarefaService tarefaService, ProjetoService projetoService) {
        this.tarefaService = tarefaService;
        this.projetoService = projetoService;
    }

    @PostMapping("{projetoId}")
    public ResponseEntity salvar(@PathVariable(value = "projetoId") long projetoId, @Valid @RequestBody TarefaInputDTO tarefaInputDTO, BindingResult result) {
        Response response = new Response();

        Projeto projeto = projetoService.buscarPeloId(projetoId);
        if(projeto == null) {
            response.getErrors().add("Projeto não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if(result.hasErrors()) {
            result.getAllErrors().stream().forEach(error-> {
                response.getErrors().add(error.getDefaultMessage());
            });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Tarefa tarefa = tarefaInputDTO.buildTarefa();
        tarefa.setStatus(StatusTarefaEnum.EM_ABERTO);
        tarefa.setProjeto(projeto);

        tarefa = tarefaService.salvarTarefa(tarefa);
        response.setData(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
