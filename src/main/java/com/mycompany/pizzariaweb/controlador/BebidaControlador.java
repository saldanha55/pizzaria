package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.BebidaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Bebida;
import java.io.IOException;
import java.sql.SQLException;
// --- CORREÇÃO ---
// Importações atualizadas para o padrão Jakarta EE
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BebidaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        BebidaDao bebidaDao = new BebidaDao();

        try {
            if ("cadastrar".equals(acao)) {
                Bebida bebida = new Bebida();
                bebida.setNome(request.getParameter("nome"));
                bebida.setPreco(Double.parseDouble(request.getParameter("preco")));
                bebida.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                
                bebidaDao.inserir(bebida);
                response.sendRedirect("CadastroBebida.jsp?status=sucesso");
                
            } else if ("alterar".equals(acao)) {
                Bebida bebida = new Bebida();
                bebida.setCodBebida(Integer.parseInt(request.getParameter("codBebida")));
                bebida.setNome(request.getParameter("nome"));
                bebida.setPreco(Double.parseDouble(request.getParameter("preco")));
                bebida.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                
                bebidaDao.alterar(bebida);
                response.sendRedirect("CadastroBebida.jsp?status=alterado");

            } else if ("excluir".equals(acao)) {
                Bebida bebida = new Bebida();
                bebida.setCodBebida(Integer.parseInt(request.getParameter("codBebida")));
                
                bebidaDao.excluir(bebida);
                response.sendRedirect("CadastroBebida.jsp?status=excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("CadastroBebida.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
