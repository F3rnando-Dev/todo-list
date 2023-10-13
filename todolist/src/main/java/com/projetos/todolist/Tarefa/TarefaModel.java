package com.projetos.todolist.Tarefa;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name = "TB_TAREFAS")
public class TarefaModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;


    private String idLogin;

    @Column(length = 50)
    private String titulo;

    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String prioridade;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}
