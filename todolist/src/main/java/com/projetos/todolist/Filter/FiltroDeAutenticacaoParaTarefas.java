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

        byte[] autorizacaoDescripto = Base64.getDecoder().decode(autorizacaoCripto);


        System.out.println("Auth: " +autorizacaoCripto);
        System.out.println("Senha: "+autorizacaoDescripto);
        filterChain.doFilter(request,response);
    }
}
