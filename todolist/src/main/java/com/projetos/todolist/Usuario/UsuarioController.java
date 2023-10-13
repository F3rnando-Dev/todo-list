package com.projetos.todolist.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/")
    public ResponseEntity criaUsuario(@RequestBody UsuarioModel usuarioModel){
        var login = this.usuarioRepository.findByLogin(usuarioModel.getLogin());
        if (login != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário " +login.getLogin()+" já cadastrado!");
        }

        var senhaHash = BCrypt.withDefaults().hashToString(12, usuarioModel.getSenha().toCharArray());
        usuarioModel.setSenha(senhaHash);

        var usuarioCriado = this.usuarioRepository.save(usuarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário "+usuarioCriado.getLogin()+" criado com sucesso! \n\n"
                + usuarioCriado);
    }


}
