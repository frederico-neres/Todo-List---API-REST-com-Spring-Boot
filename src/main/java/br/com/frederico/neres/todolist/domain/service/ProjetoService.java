package br.com.frederico.neres.todolist.domain.service;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    public ProjetoRepository projetoRepository;

    public ProjetoService (ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }


    public ResponseEntity buscarTodos() {
        Response<List<Projeto>> response = new Response<List<Projeto>>();

        List<Projeto> projeto = projetoRepository.findAll();
        response.setData(projeto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity buscarTarefas(Long idProjeto) {
        Response<List<Tarefa>> response = new Response<List<Tarefa>>();
        Projeto projeto = projetoRepository.findById(idProjeto).orElse(null);

        if(projeto == null) {
            response.getErrors().add("Produto não encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.setData(projeto.getTarefas());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity salvar(Projeto projetoInput, BindingResult result) {
        Response<Projeto> response = new Response<Projeto>();
        Boolean hasErrorsBadRequest = false;

        if(result.hasErrors()) {
            hasErrorsBadRequest = true;

            result.getAllErrors().forEach(erro -> {
                response.getErrors().add(erro.getDefaultMessage());
            });
        }
        boolean existsByTitulo = projetoRepository.existsByTitulo(projetoInput.getTitulo());
        boolean existsByDescricao = projetoRepository.existsByDescricao(projetoInput.getDescricao());

        if(existsByTitulo) {
            response.getErrors().add("Já existe um projeto com esse título!");
            hasErrorsBadRequest = true;
        }

        if(existsByDescricao) {
            response.getErrors().add("Já existe um projeto com essa descrição!");
            hasErrorsBadRequest = true;
        }

        if(hasErrorsBadRequest == true) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Projeto projeto = projetoRepository.save(projetoInput);
        response.setData(projeto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
