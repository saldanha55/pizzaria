/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.ClienteDao;
import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.dao.ItensVendaDao;
import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.RecheioBordaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.modelo.entidade.ItensVenda;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.RecheioBorda;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.mycompany.pizzariaweb.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstantes.BASE_PATH + "/ItensVendaControlador")
public class ItensVendaControlador extends HttpServlet {
    private FuncionarioDao funcionarioDao;
    private Cliente cliente;
    private ClienteDao clienteDao;
    private ItensVenda itensVenda;
    private ItensVendaDao itensVendaDao;
    private PizzaDao pizzaDao;
    String pizzaVenda="", codPedido="", quantidade="", valor="", codItensVenda="",
        itemVendido=""; // itemVendido -> qualquer itemvenda ja existente
    // Cliente_codCliente = "";

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        cliente = new Cliente();
        clienteDao = new ClienteDao();
        itensVenda = new ItensVenda();
        itensVendaDao = new ItensVendaDao();
        pizzaDao = new PizzaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            pizzaVenda = request.getParameter("pizzaVenda");
            codPedido = request.getParameter("codPedido");
            quantidade = request.getParameter("quantidade");
            itemVendido = request.getParameter("itemVendido");
            codItensVenda = request.getParameter("codItensVenda");

            switch (opcao) {
                case "Vender":
                    vender(request, response);
                    break;
                case "Cancelar":
                    cancelar(request, response);
                    break;
                case "listar":
                    encaminharParaPagina(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetro não são numeros válidos" + e);
        } catch (IllegalArgumentException e) {
            response.getWriter().println(" Erro: valor do parâmetro ausente " + e);
        }

    }

    private void vender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(pizzaVenda == null || pizzaVenda.isEmpty())
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        
        Pizza pizzaVendida = pizzaDao.buscarPorId(Integer.valueOf(pizzaVenda));
        itensVenda.setQuantidade(Integer.valueOf(quantidade));
        itensVenda.setValor(pizzaVendida.getPrecoBase() * Integer.valueOf(quantidade)); // Para calculo do valor total a pagar
        itensVenda.getObjItens_codVenda().setCodPedido(Integer.valueOf(codPedido));
        itensVenda.getObjItens_codPizza().setCodPizza(Integer.valueOf(pizzaVenda));
        itensVendaDao.salvar(itensVenda);
        
        request.setAttribute("codPedido", codPedido);
        request.setAttribute("opcao", "listar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        request.setAttribute("funcionarios", funcionarios);
        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);
        List<Pizza> pizzas = pizzaDao.buscarTodas();
        request.setAttribute("pizzas", pizzas);
        List<ItensVenda> itensVendas = itensVendaDao.buscarTodosPorId(Integer.valueOf(codPedido));
        request.setAttribute("itensVendas", itensVendas);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroItensVenda.jsp");
        dispatcher.forward(request, response);

    }

    
    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itensVenda = itensVendaDao.buscarPorId(Integer.valueOf(codPedido));
        itensVendaDao.excluir(itensVenda);
        
        request.setAttribute("codPedido", codPedido);
        request.setAttribute("opcao", "listar");
        encaminharParaPagina(request, response);
    }

}
