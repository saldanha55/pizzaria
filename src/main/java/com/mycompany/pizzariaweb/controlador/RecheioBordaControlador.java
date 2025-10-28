package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.RecheioBordaDao;
import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecheioBordaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        RecheioBordaDao recheioBordaDao = new RecheioBordaDao();

        try {
            if ("cadastrar".equals(acao)) {
                RecheioBorda borda = new RecheioBorda();
                borda.setSabor(request.getParameter("sabor"));
                borda.setPreco(Double.parseDouble(request.getParameter("preco")));
                
                recheioBordaDao.inserir(borda);
                response.sendRedirect("CadastroRecheioBorda.jsp?status=sucesso");
                
            } else if ("alterar".equals(acao)) {
                RecheioBorda borda = new RecheioBorda();
                borda.setCodBorda(Integer.parseInt(request.getParameter("codBorda")));
                borda.setSabor(request.getParameter("sabor"));
                borda.setPreco(Double.parseDouble(request.getParameter("preco")));
                
                recheioBordaDao.alterar(borda);
                response.sendRedirect("CadastroRecheioBorda.jsp?status=alterado");

            } else if ("excluir".equals(acao)) {
                RecheioBorda borda = new RecheioBorda();
                borda.setCodBorda(Integer.parseInt(request.getParameter("codBorda")));
                
                recheioBordaDao.excluir(borda);
                response.sendRedirect("CadastroRecheioBorda.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroRecheioBorda.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
