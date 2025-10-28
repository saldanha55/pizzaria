package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.UsuarioDao;
import com.mycompany.pizzariaweb.modelo.entidade.Usuario;
import com.mycompany.pizzariaweb.servico.EmailServico;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class SenhaControlador extends HttpServlet {

    private UsuarioDao usuarioDao;
    private EmailServico emailServico;

    @Override
    public void init() {
        this.usuarioDao = new UsuarioDao();
        this.emailServico = new EmailServico();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Usuario usuario = usuarioDao.buscarPorToken(token);
            if (usuario != null) {
                // Token válido, encaminha para a página de redefinição
                request.setAttribute("token", token);
                request.getRequestDispatcher("redefinir-senha.jsp").forward(request, response);
            } else {
                // Token inválido ou expirado
                request.setAttribute("mensagemErro", "Token de redefinição inválido ou expirado.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados ao validar o token.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            if ("solicitar".equals(acao)) {
                solicitarReset(request, response);
            } else if ("redefinir".equals(acao)) {
                redefinirSenha(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados no processo de senha.", e);
        }
    }

    private void solicitarReset(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        String email = request.getParameter("email");
        Usuario usuario = usuarioDao.buscarPorEmail(email);

        if (usuario != null) {
            String token = UUID.randomUUID().toString();
            usuario.setTokenResetSenha(token);
            usuarioDao.salvarToken(usuario);

            // Monta o link de redefinição
            String linkReset = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/SenhaControlador?token=" + token;

            // Envia o e-mail (você precisa configurar o EmailServico)
            emailServico.enviarEmailRedefinicao(email, linkReset);

            request.setAttribute("mensagemSucesso", "Se o e-mail existir em nosso sistema, um link para redefinição de senha será enviado.");
        } else {
            // Mesmo que o e-mail não exista, mostramos a mesma mensagem por segurança.
            request.setAttribute("mensagemSucesso", "Se o e-mail existir em nosso sistema, um link para redefinição de senha será enviado.");
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void redefinirSenha(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        String token = request.getParameter("token");
        String novaSenha = request.getParameter("novaSenha");
        String confirmarSenha = request.getParameter("confirmarSenha");

        if (!novaSenha.equals(confirmarSenha)) {
            request.setAttribute("mensagemErro", "As senhas não conferem.");
            request.setAttribute("token", token);
            request.getRequestDispatcher("redefinir-senha.jsp").forward(request, response);
            return;
        }

        Usuario usuario = usuarioDao.buscarPorToken(token);

        if (usuario != null) {
            usuario.setSenha(novaSenha); // A senha será 'hasheada' no DAO
            usuarioDao.alterarSenhaComToken(usuario);

            request.setAttribute("mensagemSucesso", "Sua senha foi redefinida com sucesso!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("mensagemErro", "Token inválido ou expirado. Por favor, solicite um novo link.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}