package com.projetos.todolist.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "TB_USUARIOS")
public class UsuarioModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String login;

    private String nome;
    private Long cpf;
    private String senha;

    @CreationTimestamp
    private LocalDateTime criadoEm;


    
     
}
