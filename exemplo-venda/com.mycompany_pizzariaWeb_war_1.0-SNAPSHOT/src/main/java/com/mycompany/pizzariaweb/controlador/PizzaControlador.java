/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.RecheioBordaDao;
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
@WebServlet(WebConstantes.BASE_PATH + "/PizzaControlador")
public class PizzaControlador extends HttpServlet {

    private Pizza objPizza;
    private PizzaDao pizzaDao;
    private RecheioBorda objRecheioBorda;
    private RecheioBordaDao recheioBordaDao;
    String codPizza="", nome="", precoBase="", pizza_borda="";

    @Override
    public void init() throws ServletException {
        pizzaDao = new PizzaDao();
        objPizza = new Pizza();
        objRecheioBorda = new RecheioBorda();
        recheioBordaDao = new RecheioBordaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codPizza = request.getParameter("codPizza");
            nome = request.getParameter("nome");
            precoBase = request.getParameter("precoBase");
            pizza_borda = request.getParameter("pizza_borda");
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
        objPizza.setNome(nome);
        objPizza.setPrecoBase(Double.valueOf(precoBase));
        objPizza.getPizza_borda().setCodigoRecheioBorda(Integer.valueOf(pizza_borda));
        pizzaDao.salvar(objPizza);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pizza> pizzas = pizzaDao.buscarTodas();
        request.setAttribute("pizzas", pizzas);
        List<RecheioBorda> recheioBordas = recheioBordaDao.buscarTodas();
        request.setAttribute("recheioBordas", recheioBordas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPizza.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPizza", codPizza);
        request.setAttribute("nome", nome);
        request.setAttribute("precoBase", precoBase);
        request.setAttribute("pizza_borda", pizza_borda);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPizza", codPizza);
        request.setAttribute("nome", nome);
        request.setAttribute("precoBase", precoBase);
        request.setAttribute("pizza_borda", pizza_borda);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objPizza.setCodPizza(Integer.valueOf(codPizza));
        objPizza.setNome(nome);
        objPizza.setPrecoBase(Double.valueOf(precoBase));
        objPizza.getPizza_borda().setCodigoRecheioBorda(Integer.valueOf(pizza_borda));
        pizzaDao.alterar(objPizza);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objPizza.setCodPizza(Integer.valueOf(codPizza));
        pizzaDao.excluir(objPizza);
        encaminharParaPagina(request, response);
    }

    public void validaCampos() {
        if ((nome == null) || (nome.isEmpty()) || (precoBase == null) || (precoBase.isEmpty()) || (pizza_borda == null) || (pizza_borda.isEmpty())) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");

        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPizza", 0);
        request.setAttribute("nome", "");
        request.setAttribute("precoBase", "");
        request.setAttribute("pizza_borda", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

}
