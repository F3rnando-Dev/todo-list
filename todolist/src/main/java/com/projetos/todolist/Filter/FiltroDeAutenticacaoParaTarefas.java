package com.projetos.todolist.Filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.projetos.todolist.Usuario.UsuarioRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FiltroDeAutenticacaoParaTarefas extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/tarefas/")) {
            var autorizacao = request.getHeader("Authorization");
            var autorizacaoCripto = autorizacao.substring("Basic".length()).trim();

            byte[] autorizacaoBase64 = Base64.getDecoder().decode(autorizacaoCripto);

            String autorizacaoString = new String(autorizacaoBase64);

            String[] credencial = autorizacaoString.split(":");
            String loginCredencial = credencial[0];
            String senhaCredencial = credencial[1];


            var usuario = this.usuarioRepository.findByLogin(loginCredencial);
            if (usuario == null) {
                response.sendError(401, "Usuário sem autorização!");
            } else {
                var verificacaoSenha = BCrypt.verifyer().verify(senhaCredencial.toCharArray(), usuario.getSenha());
                if (verificacaoSenha.verified) {
                    request.setAttribute("idLogin", usuario.getId());
                    System.out.println(request.getAttribute("idLogin"));
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else{
            filterChain.doFilter(request,response);
        }
    }
}