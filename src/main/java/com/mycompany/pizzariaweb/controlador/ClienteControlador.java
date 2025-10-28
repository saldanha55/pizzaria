package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.ClienteDao;
import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ClienteControlador extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        ClienteDao clienteDao = new ClienteDao();
        
        if(acao.isEmpty()){
                            response.sendRedirect("CadastroCliente.jsp");

        }
        
        try {
            if ("cadastrar".equals(acao)) {
                Cliente cliente = new Cliente();
                cliente.setNome(request.getParameter("nome"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setBairro(request.getParameter("bairro"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setNumero(request.getParameter("numero"));
                cliente.setEmail(request.getParameter("email"));
                
                clienteDao.inserir(cliente);
                response.sendRedirect("CadastroCliente.jsp?status=sucesso");
            } 
            else if ("alterar".equals(acao)) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));
                cliente.setNome(request.getParameter("nome"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setBairro(request.getParameter("bairro"));
                cliente.setRua(request.getParameter("rua"));
                cliente.setNumero(request.getParameter("numero"));
                cliente.setEmail(request.getParameter("email"));

                clienteDao.alterar(cliente);
                response.sendRedirect("CadastroCliente.jsp?status=alterado");
            }
            else if ("excluir".equals(acao)) {
                Cliente cliente = new Cliente();
                cliente.setCodCliente(Integer.parseInt(request.getParameter("codCliente")));
                
                clienteDao.excluir(cliente);
                response.sendRedirect("CadastroCliente.jsp?status=excluido");
            }
        } catch (SQLException e) {
            response.sendRedirect("CadastroCliente.jsp?status=erro&msg=" + e.getMessage());
        }
    }
}
