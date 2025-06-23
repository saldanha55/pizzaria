/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.IngredienteDao;
import com.mycompany.pizzariaweb.modelo.dao.Ingrediente_por_pizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente_por_pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
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
@WebServlet(WebConstantes.BASE_PATH + "/Ingrediente_por_pizzaControlador")
public class Ingrediente_por_pizzaControlador extends HttpServlet {

    private Ingrediente_por_pizza objIngrediente_por_pizza;
    private Ingrediente_por_pizzaDao ingrediente_por_pizzaDao;
    private Pizza objPizza;
    private PizzaDao pizzaDao;
    private Ingrediente objIngrediente;
    private IngredienteDao ingredienteDao;
    String codigo="", pizza_codPizza="", ingrediente_codIngrediente="", quantIngrediente="";

    @Override
    public void init() throws ServletException {
        ingrediente_por_pizzaDao = new Ingrediente_por_pizzaDao();
        objIngrediente_por_pizza = new Ingrediente_por_pizza();
        objPizza = new Pizza();
        pizzaDao = new PizzaDao();
        objIngrediente = new Ingrediente();
        ingredienteDao = new IngredienteDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codigo = request.getParameter("codigo");
            pizza_codPizza = request.getParameter("pizza_codPizza");
            ingrediente_codIngrediente = request.getParameter("ingrediente_codIngrediente");
            quantIngrediente = request.getParameter("quantIngrediente");
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
        objIngrediente_por_pizza.getPizza_codPizza().setCodPizza(Integer.valueOf(pizza_codPizza));
        objIngrediente_por_pizza.getIngrediente_codIngrediente().setCodigoIngrediente(Integer.valueOf(ingrediente_codIngrediente));
        objIngrediente_por_pizza.setQuantIngrediente(Float.valueOf(quantIngrediente));
        ingrediente_por_pizzaDao.salvar(objIngrediente_por_pizza);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ingrediente_por_pizza> ingredientes_por_pizzas = ingrediente_por_pizzaDao.buscarTodas();
        request.setAttribute("ingredientes_por_pizzas", ingredientes_por_pizzas);
        List<Pizza> pizzas = pizzaDao.buscarTodas();
        request.setAttribute("pizzas", pizzas);
        List<Ingrediente> ingredientes = ingredienteDao.buscarTodas();
        request.setAttribute("ingredientes", ingredientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroIngrediente_por_pizza.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("pizza_codPizza", pizza_codPizza);
        request.setAttribute("ingrediente_codIngrediente", ingrediente_codIngrediente);
        request.setAttribute("quantIngrediente", quantIngrediente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e depois clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("pizza_codPizza", pizza_codPizza);
        request.setAttribute("ingrediente_codIngrediente", ingrediente_codIngrediente);
        request.setAttribute("quantIngrediente", quantIngrediente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objIngrediente_por_pizza.setCodigo(Integer.valueOf(codigo));
        objIngrediente_por_pizza.getPizza_codPizza().setCodPizza(Integer.valueOf(pizza_codPizza));
        objIngrediente_por_pizza.getIngrediente_codIngrediente().setCodigoIngrediente(Integer.valueOf(ingrediente_codIngrediente));
        objIngrediente_por_pizza.setQuantIngrediente(Float.valueOf(quantIngrediente));
        ingrediente_por_pizzaDao.alterar(objIngrediente_por_pizza);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objIngrediente_por_pizza.setCodigo(Integer.valueOf(codigo));
        ingrediente_por_pizzaDao.excluir(objIngrediente_por_pizza);
        encaminharParaPagina(request, response);
    }

    public void validaCampos() {
        if ((pizza_codPizza == null) || (pizza_codPizza.isEmpty()) || (ingrediente_codIngrediente == null) || (ingrediente_codIngrediente.isEmpty()) || (quantIngrediente == null) || (quantIngrediente.isEmpty())) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");

        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", 0);
        request.setAttribute("pizza_codPizza", "");
        request.setAttribute("ingrediente_codIngrediente", "");
        request.setAttribute("quantIngrediente", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

}
