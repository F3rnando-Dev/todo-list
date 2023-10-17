package com.projetos.todolist.Tarefa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<TarefaModel, UUID> {
    List<TarefaModel> findByIdLogin(UUID idLogin);

}
