package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.ItensVendaDao;
import com.mycompany.pizzariaweb.modelo.entidade.ItensVenda;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ATENÇÃO: Este controlador é em grande parte obsoleto.
 * A lógica de adicionar e remover itens do carrinho agora é gerenciada
 * diretamente na página 'CadastroVenda.jsp' para uma melhor experiência do usuário.
 * Este controlador pode ser usado para futuras telas de administração de itens.
 */
public class ItensVendaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        ItensVendaDao itensVendaDao = new ItensVendaDao();

        try {
            if ("excluir".equals(acao)) {
                int codItem = Integer.parseInt(request.getParameter("codItensVenda"));
                ItensVenda item = new ItensVenda();
                item.setCodItensVenda(codItem);
                
                itensVendaDao.excluir(item);
                // Redirecionar para uma página de administração de vendas, se existir.
                response.sendRedirect("AdminVendas.jsp?status=item_excluido");
            }
        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("AdminVendas.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
