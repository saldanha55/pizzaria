package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.dao.UsuarioDao;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.modelo.entidade.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UsuarioControlador extends HttpServlet {

    private UsuarioDao usuarioDao;
    private FuncionarioDao funcionarioDao;

    @Override
    public void init() {
        this.usuarioDao = new UsuarioDao();
        this.funcionarioDao = new FuncionarioDao();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redireciona para a página de cadastro quando acessado via GET
        response.sendRedirect("CadastroUsuario.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao == null) {
            acao = "salvar"; // Ação padrão é salvar
        }

        try {
            switch (acao) {
                case "salvar":
                    salvar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados no UsuarioControlador", e);
        }
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
                
        // --- Ponto de Debug: Verifique se os dados estão chegando do formulário ---
        System.out.println("UsuarioControlador: Iniciando processo de salvar...");
        
        String codUsuarioStr = request.getParameter("codUsuario");
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String codFuncionarioStr = request.getParameter("codFuncionario");
        
        System.out.println("Dados recebidos -> login: " + login + ", email: " + email + ", codFuncionario: " + codFuncionarioStr);

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        try {
            // 1. Validação Crucial: Verificar se o código do funcionário é válido
            int codFuncionario = Integer.parseInt(codFuncionarioStr);
            Funcionario funcionario = funcionarioDao.buscarPorId(codFuncionario);

            // 2. CORREÇÃO: Interromper se o funcionário não for encontrado
            if (funcionario == null) {
                System.out.println("UsuarioControlador: ERRO - Funcionario com codigo " + codFuncionario + " nao encontrado.");
                request.setAttribute("mensagemErro", "Erro: O funcionário com o código informado não existe.");
                request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
                return; // Encerra a execução do método aqui
            }
            
            System.out.println("UsuarioControlador: Funcionario encontrado: " + funcionario.getNome());
            usuario.setFuncionario(funcionario);

            if (codUsuarioStr != null && !codUsuarioStr.isEmpty()) {
                // Alteração
                usuario.setCodUsuario(Integer.valueOf(codUsuarioStr));
                usuarioDao.alterar(usuario);
                request.setAttribute("mensagemSucesso", "Usuário alterado com sucesso!");
                System.out.println("UsuarioControlador: Usuario alterado com sucesso.");
            } else {
                // Inserção
                usuarioDao.inserir(usuario);
                request.setAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");
                System.out.println("UsuarioControlador: Usuario inserido com sucesso.");
            }
        } catch (NumberFormatException e) {
             System.out.println("UsuarioControlador: ERRO - Codigo do funcionario invalido (nao e um numero).");
             request.setAttribute("mensagemErro", "Código do funcionário inválido. Por favor, insira um número.");
        } catch (SQLException e) {
             System.out.println("UsuarioControlador: ERRO SQL - " + e.getMessage());
             request.setAttribute("mensagemErro", "Erro de banco de dados ao salvar o usuário: " + e.getMessage());
        }

        // Redireciona de volta para a página de cadastro para exibir a mensagem
        request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String codUsuarioStr = request.getParameter("codUsuario");
        try {
            int codUsuario = Integer.parseInt(codUsuarioStr);
            Usuario usuario = new Usuario();
            usuario.setCodUsuario(codUsuario);
            usuarioDao.excluir(usuario);
            request.setAttribute("mensagemSucesso", "Usuário excluído com sucesso!");
        } catch (NumberFormatException e) {
             request.setAttribute("mensagemErro", "Código de usuário inválido para exclusão.");
        } catch (SQLException e) {
             request.setAttribute("mensagemErro", "Erro ao excluir usuário: " + e.getMessage());
        }
        // Idealmente, aqui você redirecionaria para uma página de listagem de usuários
        request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
    }
}