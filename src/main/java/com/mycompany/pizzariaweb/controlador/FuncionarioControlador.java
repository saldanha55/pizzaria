package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.servico.ConverteData;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FuncionarioControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        FuncionarioDao funcionarioDao = new FuncionarioDao();

        try {
            if ("cadastrar".equals(acao)) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setCpf(request.getParameter("cpf"));
                funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
                funcionario.setCargo(request.getParameter("cargo"));
                funcionario.setBairro(request.getParameter("bairro"));
                funcionario.setRua(request.getParameter("rua"));
                funcionario.setNumero(request.getParameter("numero"));
                funcionario.setTelefone(request.getParameter("telefone"));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setNascimento(ConverteData.convertStringToDate(request.getParameter("nascimento")));
                
                funcionarioDao.inserir(funcionario);
                response.sendRedirect("CadastroFuncionario.jsp?status=sucesso");

            } else if ("alterar".equals(acao)) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodFuncionario(Integer.parseInt(request.getParameter("codFuncionario")));
                funcionario.setNome(request.getParameter("nome"));
                funcionario.setCpf(request.getParameter("cpf"));
                funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
                funcionario.setCargo(request.getParameter("cargo"));
                funcionario.setBairro(request.getParameter("bairro"));
                funcionario.setRua(request.getParameter("rua"));
                funcionario.setNumero(request.getParameter("numero"));
                funcionario.setTelefone(request.getParameter("telefone"));
                funcionario.setEmail(request.getParameter("email"));
                funcionario.setNascimento(ConverteData.convertStringToDate(request.getParameter("nascimento")));

                funcionarioDao.alterar(funcionario);
                response.sendRedirect("CadastroFuncionario.jsp?status=alterado");

            } else if ("excluir".equals(acao)) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodFuncionario(Integer.parseInt(request.getParameter("codFuncionario")));
                
                funcionarioDao.excluir(funcionario);
                response.sendRedirect("CadastroFuncionario.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroFuncionario.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
