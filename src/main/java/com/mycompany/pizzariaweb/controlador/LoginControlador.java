package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.UsuarioDao;
import com.mycompany.pizzariaweb.modelo.entidade.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class LoginControlador extends HttpServlet {

    private UsuarioDao usuarioDao;

    @Override
    public void init() {
        this.usuarioDao = new UsuarioDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("registrar".equals(acao)) {
            // Apenas redireciona para a página de cadastro de usuário.
            response.sendRedirect("CadastroUsuario.jsp?acao=registrar");
        } else {
            // O padrão para o método GET é realizar o logout para segurança.
            logout(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if ("login".equals(acao)) {
            try {
                login(request, response);
            } catch (SQLException e) {
                // Em caso de erro de SQL, lança uma exceção para a página de erro do servidor.
                throw new ServletException("Erro de banco de dados ao tentar fazer login.", e);
            }
        } else {
            // Se a ação não for 'login', redireciona de volta para a página de login.
            response.sendRedirect("login.jsp");
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, SQLException {
        String login = request.getParameter("login");
        String senhaAberta = request.getParameter("senha");

        // --- PONTO DE DEBUG 2: VERIFICAR DADOS RECEBIDOS ---
        System.out.println("LoginControlador: Tentativa de login com usuario: " + login);

        Usuario usuario = usuarioDao.buscarPorLogin(login);

        if (usuario != null && BCrypt.checkpw(senhaAberta, usuario.getSenhaHash())) {
            // --- PONTO DE DEBUG 3: SUCESSO NA AUTENTICAÇÃO ---
            System.out.println("LoginControlador: Senha CORRETA para o usuario: " + login + ". Criando sessao.");

            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario); 
            response.sendRedirect("index.jsp"); 
        } else {
            // --- PONTO DE DEBUG 4: FALHA NA AUTENTICAÇÃO ---
            if (usuario == null) {
                System.out.println("LoginControlador: Usuario '" + login + "' NAO ENCONTRADO no banco.");
            } else {
                System.out.println("LoginControlador: Senha INCORRETA para o usuario: " + login);
            }
            
            request.setAttribute("mensagemErro", "Usuário ou senha inválidos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false); // Obtém a sessão sem criar uma nova
        if (session != null) {
            session.invalidate(); // Invalida a sessão, removendo todos os atributos
        }
        response.sendRedirect("login.jsp"); // Redireciona para a página de login
    }
}