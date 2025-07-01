/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.ClienteDao;
import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.VendaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
import com.mycompany.pizzariaweb.modelo.entidade.Pizza;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
import com.mycompany.pizzariaweb.servico.ConverteData;
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
@WebServlet(WebConstantes.BASE_PATH + "/VendaControlador")
public class VendaControlador extends HttpServlet {

    private Venda objVenda;
    private VendaDao vendaDao;
    private Cliente objCliente;
    private ClienteDao clienteDao;
    private Funcionario objFuncionario;
    private FuncionarioDao funcionarioDao;
    private PizzaDao pizzaDao;
    private final ConverteData converte = new ConverteData();
    String codPedido="", data="", tipoPagamento="", desconto="", comissao="", cliente_codCliente="", funcionario_codFuncionario="";

    @Override
    public void init() throws ServletException {
        vendaDao = new VendaDao();
        objVenda = new Venda();
        objCliente = new Cliente();
        clienteDao = new ClienteDao();
        objFuncionario = new Funcionario();
        funcionarioDao = new FuncionarioDao();
        pizzaDao = new PizzaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String opcao = request.getParameter("opcao");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            codPedido = request.getParameter("codPedido");
            data = request.getParameter("data");
            tipoPagamento = request.getParameter("tipoPagamento");
            desconto = request.getParameter("desconto");
            comissao = request.getParameter("comissao");
            cliente_codCliente = request.getParameter("cliente_codCliente");
            funcionario_codFuncionario = request.getParameter("funcionario_codFuncionario");
            switch (opcao) {
                case "Cadastrar":
                    cadastrar(request, response);
                    break;
                case "Finalizar":
                    finalizar(request, response);
                    break;
                case "Cancelar":
                    cancelar(request, response);
                    break;
                case "cancelar":
                case "listar":
                    request.setAttribute("codPedido", "0");
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

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objVenda.setData(converte.converteCalendario(data));
        objVenda.setTipoPagamento((tipoPagamento == null || tipoPagamento.isEmpty()) ? "dinheiro" : tipoPagamento);
        objVenda.setDesconto((desconto == null || desconto.isEmpty()) ? 0 : Double.valueOf(desconto));
        objVenda.setComissao((comissao == null || comissao.isEmpty()) ? 0 : Double.valueOf(comissao));
        objVenda.getCliente_codCliente().setCodigoCliente(Integer.valueOf(cliente_codCliente));
        objVenda.getFuncionario_codFuncionario().setCodigoFuncionario(Integer.valueOf(funcionario_codFuncionario));
        vendaDao.salvar(objVenda);
        
        request.setAttribute("codPedido", vendaDao.getLastId());
        request.setAttribute("opcao", "listar");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        request.setAttribute("funcionarios", funcionarios);
        List<Pizza> pizzas = pizzaDao.buscarTodas();
        request.setAttribute("pizzas", pizzas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroItensVenda.jsp");
        dispatcher.forward(request, response);
    }

    private void finalizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPedido", 0);
        request.setAttribute("data", "");
        /*request.setAttribute("tipoPagamento", "");
        request.setAttribute("desconto", "");
        request.setAttribute("comissao", "");*/
        request.setAttribute("cliente_codCliente", "");
        request.setAttribute("funcionario_codFuncionario", "");
        encaminharParaPagina(request, response);
    }

    /*private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPedido", codPedido);
        request.setAttribute("data", ConverteData.convertDateFormat(data));
        request.setAttribute("tipoPagamento", tipoPagamento);
        request.setAttribute("desconto", desconto);
        request.setAttribute("comissao", comissao);
        request.setAttribute("cliente_codCliente", cliente_codCliente);
        request.setAttribute("funcionario_codFuncionario", funcionario_codFuncionario);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codPedido", codPedido);
        request.setAttribute("data", ConverteData.convertDateFormat(data));
        request.setAttribute("tipoPagamento", tipoPagamento);
        request.setAttribute("desconto", desconto);
        request.setAttribute("comissao", comissao);
        request.setAttribute("cliente_codCliente", cliente_codCliente);
        request.setAttribute("funcionario_codFuncionario", funcionario_codFuncionario);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objVenda.setCodPedido(Integer.valueOf(codPedido));
        objVenda.setData(converte.converteCalendario(data));
        objVenda.setTipoPagamento(tipoPagamento);
        objVenda.setDesconto(Double.valueOf(desconto));
        objVenda.setComissao(Double.valueOf(comissao));
        objVenda.getCliente_codCliente().setCodigoCliente(Integer.valueOf(cliente_codCliente));
        objVenda.getFuncionario_codFuncionario().setCodigoFuncionario(Integer.valueOf(funcionario_codFuncionario));
        vendaDao.alterar(objVenda);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objVenda.setCodPedido(Integer.valueOf(codPedido));
        vendaDao.excluir(objVenda);
        encaminharParaPagina(request, response);
    }*/

    public void validaCampos() {
        if ((data == null) || (data.isEmpty()) ||
                (cliente_codCliente == null) || (cliente_codCliente.isEmpty()) || (funcionario_codFuncionario == null) || (funcionario_codFuncionario.isEmpty())) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");

        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objVenda.setCodPedido(Integer.valueOf(codPedido));
        vendaDao.excluir(objVenda);

        request.setAttribute("codPedido", 0);
        request.setAttribute("data", "");
        /*request.setAttribute("tipoPagamento", "");
        request.setAttribute("desconto", "");
        request.setAttribute("comissao", "");*/
        request.setAttribute("cliente_codCliente", "");
        request.setAttribute("funcionario_codFuncionario", "");
        request.setAttribute("opcao", "listar");
        encaminharParaPagina(request, response);
    }

}
