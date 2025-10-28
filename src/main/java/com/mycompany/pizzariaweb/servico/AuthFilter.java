package com.mycompany.pizzariaweb.servico;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthFilter implements Filter {

    private static final Set<String> CAMINHOS_PUBLICOS = new HashSet<>(Arrays.asList(
            "/login.jsp",
            "/esqueci-senha.jsp",
            "/redefinir-senha.jsp",
            "/CadastroUsuario.jsp"
    ));

    private static final Set<String> SERVLETS_PUBLICOS = new HashSet<>(Arrays.asList(
            "/LoginControlador",
            "/SenhaControlador",
            "/UsuarioControlador",
            "/RelatorioControlador" // Permite acesso irrestrito ao servlet de relatórios

    ));

    private static final Set<String> DIRETORIOS_PUBLICOS = new HashSet<>(Arrays.asList(
            "/css/",
            "/js/",
            "/imagens/"
    ));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Log para ajudar a depurar no console do servidor
        System.out.println("Filtro interceptou: " + path);

        HttpSession session = httpRequest.getSession(false);
        boolean isLogado = (session != null && session.getAttribute("usuarioLogado") != null);
        
        // Verifica se o caminho começa com algum dos caminhos públicos
        boolean isCaminhoPublico = CAMINHOS_PUBLICOS.stream().anyMatch(path::startsWith);

        if (isLogado || isCaminhoPublico) {
            System.out.println("--> Acesso permitido para: " + path);
            chain.doFilter(request, response);
        } else {
            System.out.println("--> Acesso negado. Redirecionando para login: " + path);
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        }
    }


    private boolean isCaminhoPublico(String path) {
        if (CAMINHOS_PUBLICOS.contains(path)) {
            return true;
        }

        if (SERVLETS_PUBLICOS.contains(path)) {
            return true;
        }

        for (String diretorio : DIRETORIOS_PUBLICOS) {
            if (path.startsWith(diretorio)) {
                return true;
            }
        }

        return false;
    }
}
