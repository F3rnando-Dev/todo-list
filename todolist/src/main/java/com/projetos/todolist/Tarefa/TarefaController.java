package com.projetos.todolist.Tarefa;

import com.projetos.todolist.Utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping("/")
    public ResponseEntity criaTarefa(@RequestBody TarefaModel tarefaModel, HttpServletRequest request){
        System.out.println("Chegou na controller "+request.getAttribute("idLogin"));
        var idRequest = request.getAttribute("idLogin");
        tarefaModel.setIdLogin((UUID) idRequest);

        var dataAtual = LocalDateTime.now();
        if(dataAtual.isAfter(tarefaModel.getDataInicio()) || dataAtual.isAfter(tarefaModel.getDataTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início/termino deve ser maior que a atual!");
        }

        if(tarefaModel.getDataInicio().isAfter(tarefaModel.getDataTermino())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que a data de término!");
        }


        var tarefa = this.tarefaRepository.save(tarefaModel);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }

    @GetMapping("/")
    public List<TarefaModel> listaTarefas(HttpServletRequest request) {
        var idLogin = request.getAttribute("idLogin");
        var tarefa = this.tarefaRepository.findByIdLogin((UUID) idLogin);
        return tarefa;
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizaTarefa(@RequestBody TarefaModel tarefaModel, HttpServletRequest request, @PathVariable UUID id) {
        var tarefa = this.tarefaRepository.findById(id).orElse(null);

        if (tarefa == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
        }

        var idRequest = request.getAttribute("idLogin");

        if (!tarefa.getIdLogin().equals(idRequest)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(tarefaModel, tarefa);
        var tarefaAtualizada = this.tarefaRepository.save(tarefa);
        return ResponseEntity.ok().body(tarefaAtualizada);
    }
}