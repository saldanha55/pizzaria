package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.FornecedorDao;
import com.mycompany.pizzariaweb.modelo.entidade.Fornecedor;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FornecedorControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        FornecedorDao fornecedorDao = new FornecedorDao();

        try {
            if ("cadastrar".equals(acao)) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setNome(request.getParameter("nome"));
                fornecedor.setTelefone(request.getParameter("telefone"));
                fornecedor.setEmail(request.getParameter("email"));
                fornecedor.setCpf(request.getParameter("cpf"));
                
                fornecedorDao.inserir(fornecedor);
                response.sendRedirect("CadastroFornecedor.jsp?status=sucesso");
            } 
            else if ("alterar".equals(acao)) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodFornecedor(Integer.parseInt(request.getParameter("codFornecedor")));
                fornecedor.setNome(request.getParameter("nome"));
                fornecedor.setTelefone(request.getParameter("telefone"));
                fornecedor.setEmail(request.getParameter("email"));
                fornecedor.setCpf(request.getParameter("cpf"));

                fornecedorDao.alterar(fornecedor);
                response.sendRedirect("CadastroFornecedor.jsp?status=alterado");
            }
            else if ("excluir".equals(acao)) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setCodFornecedor(Integer.parseInt(request.getParameter("codFornecedor")));
                
                fornecedorDao.excluir(fornecedor);
                response.sendRedirect("CadastroFornecedor.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroFornecedor.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
