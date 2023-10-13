package com.projetos.todolist.Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FiltroDeAutenticacaoParaTarefas extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var autorizacao = request.getHeader("Authorization");
        var autorizacaoCripto = autorizacao.substring("Basic".length()).trim();

        byte[] autorizacaoBase64 = Base64.getDecoder().decode(autorizacaoCripto);

        String autorizacaoString = new String(autorizacaoBase64);

        System.out.println("Auth: " +autorizacaoCripto);
        System.out.println("Senha: "+autorizacaoString);

        String[] credencial = autorizacaoString.split(":");
        String login = credencial[0];
        String senha = credencial[1];

        System.out.println("Login: "+login);
        System.out.println("Senha: "+senha);

        filterChain.doFilter(request,response);
    }
}
