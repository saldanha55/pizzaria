package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.IngredienteDao;
import com.mycompany.pizzariaweb.modelo.dao.Ingrediente_por_pizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente_por_pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Ingrediente_por_pizzaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        Ingrediente_por_pizzaDao ippDao = new Ingrediente_por_pizzaDao();

        try {
            if ("cadastrar".equals(acao)) {
                int codPizza = Integer.parseInt(request.getParameter("codPizza"));
                int codIngrediente = Integer.parseInt(request.getParameter("codIngrediente"));
                float quantidade = Float.parseFloat(request.getParameter("quantidade"));

                Pizza pizza = new PizzaDao().buscarPorId(codPizza);
                Ingrediente ingrediente = new IngredienteDao().buscarPorId(codIngrediente);

                Ingrediente_por_pizza ipp = new Ingrediente_por_pizza();
                ipp.setPizza_codPizza(pizza);
                ipp.setIngrediente_codIngrediente(ingrediente);
                ipp.setQuantIngrediente(quantidade);
                
                ippDao.inserir(ipp);
                response.sendRedirect("CadastroIngrediente_por_pizza.jsp?status=sucesso");

            } else if ("excluir".equals(acao)) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                Ingrediente_por_pizza ipp = new Ingrediente_por_pizza();
                ipp.setCodigo(codigo);
                
                ippDao.excluir(ipp);
                response.sendRedirect("CadastroIngrediente_por_pizza.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroIngrediente_por_pizza.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
