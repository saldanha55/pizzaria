package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.VendaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ATENÇÃO: Este controlador não é mais usado para o cadastro inicial de vendas.
 * Essa lógica agora está na página 'CadastroVenda.jsp'.
 * Este servlet pode ser adaptado para uma tela de administração de vendas,
 * permitindo alterar status de pagamento, aplicar descontos posteriores ou excluir vendas.
 */
public class VendaControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        VendaDao vendaDao = new VendaDao();

        try {
            // Exemplo de uma ação que poderia existir em um painel de admin
            if ("excluir_venda_completa".equals(acao)) {
                int codPedido = Integer.parseInt(request.getParameter("codPedido"));
                Venda venda = new Venda();
                venda.setCodPedido(codPedido);
                
                // ATENÇÃO: Excluir a venda irá apagar também todos os itens
                // associados a ela, devido ao 'ON DELETE CASCADE' no banco de dados.
                vendaDao.excluir(venda);
                response.sendRedirect("AdminVendas.jsp?status=venda_excluida");
            }
            // Outras ações como 'alterar_pagamento', 'aplicar_desconto_geral', etc. poderiam ser adicionadas aqui.

        } catch (SQLException | NumberFormatException e) {
            response.sendRedirect("AdminVendas.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
