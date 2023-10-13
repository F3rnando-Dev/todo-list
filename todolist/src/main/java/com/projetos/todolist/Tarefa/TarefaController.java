package com.projetos.todolist.Tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @PostMapping("/")
    public TarefaModel criaTarefa(@RequestBody TarefaModel tarefaModel){
        System.out.println("Chegou na controller");
        var tarefa = this.tarefaRepository.save(tarefaModel);
        return tarefa;
    }
}
