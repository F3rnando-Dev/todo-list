package com.projetos.todolist.Tarefa;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping("/")
    public TarefaModel criaTarefa(@RequestBody TarefaModel tarefaModel, HttpServletRequest request){
        System.out.println("Chegou na controller "+request.getAttribute("idLogin"));
        var idRequest = request.getAttribute("idLogin");
        tarefaModel.setIdLogin((UUID) idRequest);
        var tarefa = this.tarefaRepository.save(tarefaModel);
        return tarefa;
    }
}
