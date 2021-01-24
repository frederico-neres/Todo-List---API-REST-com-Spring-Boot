package br.com.frederico.neres.todolist.domain.service;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    public ProjetoRepository projetoRepository;

    public ProjetoService (ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }


    public List<Projeto> buscarTodos() {
        return null;
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

    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }
}
