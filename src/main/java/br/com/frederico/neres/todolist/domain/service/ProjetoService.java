package br.com.frederico.neres.todolist.domain.service;

import br.com.frederico.neres.todolist.controller.response.Response;
import br.com.frederico.neres.todolist.domain.model.Projeto;
import br.com.frederico.neres.todolist.domain.model.Tarefa;
import br.com.frederico.neres.todolist.domain.repository.ProjetoRepository;
import br.com.frederico.neres.todolist.dto.input.ProjetoInputDTO;
import br.com.frederico.neres.todolist.dto.output.ProjetoOutputDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    public ProjetoRepository projetoRepository;

    public ProjetoService (ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }


    public ResponseEntity buscarTodos() {
        Response<List<ProjetoOutputDTO>> response = new Response<List<ProjetoOutputDTO>>();


      List<ProjetoOutputDTO> projetoOutputDTOList = projetoRepository.findAll().stream().map(projeto->new ProjetoOutputDTO.Builder()
                .id(projeto.getId())
                .titulo(projeto.getTitulo())
                .descricao(projeto.getDescricao())
                .build()).collect(Collectors.toList());

        response.setData(projetoOutputDTOList);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public Projeto buscarPeloId(Long id) {
        return projetoRepository.findById(id).orElse(null);
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

    public ResponseEntity salvar(ProjetoInputDTO projetoInputDTO, BindingResult result) {
        Response<ProjetoOutputDTO> response = new Response<ProjetoOutputDTO>();
        Boolean hasErrorsBadRequest = false;

        if(result.hasErrors()) {
            hasErrorsBadRequest = true;

            result.getAllErrors().forEach(erro -> {
                response.getErrors().add(erro.getDefaultMessage());
            });
        }
        boolean existsByTitulo = projetoRepository.existsByTitulo(projetoInputDTO.getTitulo());
        boolean existsByDescricao = projetoRepository.existsByDescricao(projetoInputDTO.getDescricao());

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

        Projeto projeto = projetoRepository.save(projetoInputDTO.buildProjeto());
        ProjetoOutputDTO projetoOutputDTO = new ProjetoOutputDTO.Builder()
                .id(projeto.getId())
                .titulo(projeto.getTitulo())
                .descricao(projeto.getDescricao())
                .build();

        response.setData(projetoOutputDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
