package com.projetos.todolist.Tarefa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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
}
