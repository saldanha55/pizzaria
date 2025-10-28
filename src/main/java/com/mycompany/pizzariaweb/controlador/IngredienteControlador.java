package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.IngredienteDao;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IngredienteControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        IngredienteDao ingredienteDao = new IngredienteDao();

        try {
            if ("cadastrar".equals(acao)) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setNome(request.getParameter("nome"));
                ingrediente.setPreco(Double.parseDouble(request.getParameter("preco")));
                ingrediente.setQuantEstoque(Float.parseFloat(request.getParameter("quantEstoque")));
                
                ingredienteDao.inserir(ingrediente);
                response.sendRedirect("CadastroIngrediente.jsp?status=sucesso");
                
            } else if ("alterar".equals(acao)) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setCodIngrediente(Integer.parseInt(request.getParameter("codIngrediente")));
                ingrediente.setNome(request.getParameter("nome"));
                ingrediente.setPreco(Double.parseDouble(request.getParameter("preco")));
                ingrediente.setQuantEstoque(Float.parseFloat(request.getParameter("quantEstoque")));
                
                ingredienteDao.alterar(ingrediente);
                response.sendRedirect("CadastroIngrediente.jsp?status=alterado");

            } else if ("excluir".equals(acao)) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setCodIngrediente(Integer.parseInt(request.getParameter("codIngrediente")));
                
                ingredienteDao.excluir(ingrediente);
                response.sendRedirect("CadastroIngrediente.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroIngrediente.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
