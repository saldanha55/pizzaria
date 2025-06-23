/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.Pizza_por_vendaDao;
import com.mycompany.pizzariaweb.modelo.dao.VendaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza_por_venda;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import com.mycompany.pizzariaweb.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tulio
 */
@WebServlet(WebConstantes.BASE_PATH + "/Pizza_por_vendaControlador")
public class Pizza_por_vendaControlador extends HttpServlet {

    private Pizza_por_venda objPizza_por_venda;
    private Pizza_por_vendaDao pizza_por_vendaDao;
    private Venda objVenda;
    private VendaDao vendaDao;
    private Pizza objPizza;
    private PizzaDao pizzaDao;
    String codigo="", venda_codPedido="", pizza_codPizza="", quantidade="", tamanho="", precoVenda="";

    @Override
    public void init() throws ServletException {
        pizza_por_vendaDao = new Pizza_por_vendaDao();
        objPizza_por_venda = new Pizza_por_venda();
        vendaDao = new VendaDao();
        objVenda = new Venda();
        pizzaDao = new PizzaDao();
        objPizza = new Pizza();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codigo = request.getParameter("codigo");
            venda_codPedido = request.getParameter("venda_codPedido");
            pizza_codPizza = request.getParameter("pizza_codPizza");
            quantidade = request.getParameter("quantidade");
            tamanho = request.getParameter("tamanho");
            precoVenda = request.getParameter("precoVenda");
            
            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
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

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objPizza_por_venda.getVenda_codPedido().setCodPedido(Integer.valueOf(venda_codPedido));
        objPizza_por_venda.getPizza_codPizza().setCodPizza(Integer.valueOf(pizza_codPizza));
        objPizza_por_venda.setQuantidade(Integer.valueOf(quantidade));
        objPizza_por_venda.setTamanho(tamanho);
        objPizza_por_venda.setPrecoVenda(Double.valueOf(precoVenda));
        
        pizza_por_vendaDao.salvar(objPizza_por_venda);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pizza_por_venda> pizzas_por_vendas = pizza_por_vendaDao.buscarTodas();
        request.setAttribute("pizzas_por_vendas", pizzas_por_vendas);
        List<Venda> vendas = vendaDao.buscarTodas();
        request.setAttribute("vendas", vendas);
        List<Pizza> pizzas = pizzaDao.buscarTodas();
        request.setAttribute("pizzas", pizzas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPizza_por_venda.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("venda_codPedido", venda_codPedido);
        request.setAttribute("pizza_codPizza", pizza_codPizza);
        request.setAttribute("tamanho", tamanho);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("precoVenda", precoVenda);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("venda_codPedido", venda_codPedido);
        request.setAttribute("pizza_codPizza", pizza_codPizza);
        request.setAttribute("tamanho", tamanho);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("precoVenda", precoVenda);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objPizza_por_venda.setCodigo(Integer.valueOf(codigo));
        objPizza_por_venda.getVenda_codPedido().setCodPedido(Integer.valueOf(venda_codPedido));
        objPizza_por_venda.getPizza_codPizza().setCodPizza(Integer.valueOf(pizza_codPizza));
        objPizza_por_venda.setQuantidade(Integer.valueOf(quantidade));
        objPizza_por_venda.setTamanho(tamanho);
        objPizza_por_venda.setPrecoVenda(Double.valueOf(precoVenda));
        
        pizza_por_vendaDao.alterar(objPizza_por_venda);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objPizza_por_venda.setCodigo(Integer.valueOf(codigo));
        pizza_por_vendaDao.excluir(objPizza_por_venda);
        encaminharParaPagina(request, response);
    }

    public void validaCampos() {
        if ((tamanho==null) || (tamanho.isEmpty()) || (venda_codPedido==null) || (venda_codPedido.isEmpty()) || (pizza_codPizza==null) || (pizza_codPizza.isEmpty()) || (quantidade==null) || (quantidade.isEmpty()) || (precoVenda==null) || (precoVenda.isEmpty())) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");

        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", 0);
        request.setAttribute("venda_codPedido", "");
        request.setAttribute("pizza_codPizza", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("tamanho", "");
        request.setAttribute("precoVenda", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

}
