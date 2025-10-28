package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.RecheioBordaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PizzaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        PizzaDao pizzaDao = new PizzaDao();
        RecheioBordaDao bordaDao = new RecheioBordaDao();

        try {
            if ("cadastrar".equals(acao)) {
                Pizza pizza = new Pizza();
                pizza.setNome(request.getParameter("nome"));
                pizza.setPrecoBase(Double.parseDouble(request.getParameter("precoBase")));
                pizza.setTamanho(request.getParameter("tamanho"));
                
                int codBorda = Integer.parseInt(request.getParameter("borda"));
                RecheioBorda borda = bordaDao.buscarPorId(codBorda);
                pizza.setBorda(borda);
                
                pizzaDao.inserir(pizza);
                response.sendRedirect("CadastroPizza.jsp?status=sucesso");

            } else if ("alterar".equals(acao)) {
                Pizza pizza = new Pizza();
                pizza.setCodPizza(Integer.parseInt(request.getParameter("codPizza")));
                pizza.setNome(request.getParameter("nome"));
                pizza.setPrecoBase(Double.parseDouble(request.getParameter("precoBase")));
                pizza.setTamanho(request.getParameter("tamanho"));

                int codBorda = Integer.parseInt(request.getParameter("borda"));
                RecheioBorda borda = bordaDao.buscarPorId(codBorda);
                pizza.setBorda(borda);

                pizzaDao.alterar(pizza);
                response.sendRedirect("CadastroPizza.jsp?status=alterado");

            } else if ("excluir".equals(acao)) {
                Pizza pizza = new Pizza();
                pizza.setCodPizza(Integer.parseInt(request.getParameter("codPizza")));
                
                pizzaDao.excluir(pizza);
                response.sendRedirect("CadastroPizza.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroPizza.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
