package com.projetos.todolist.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/")
    public UsuarioModel criaUsuario(@RequestBody UsuarioModel usuarioModel){

        var usuarioCriado = this.usuarioRepository.save(usuarioModel);
        return usuarioCriado;
    }


}
