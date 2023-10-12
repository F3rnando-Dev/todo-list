package com.projetos.todolist.Usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, UUID>{
    



}
