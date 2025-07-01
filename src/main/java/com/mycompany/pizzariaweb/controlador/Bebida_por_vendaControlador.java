/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.BebidaDao;
import com.mycompany.pizzariaweb.modelo.dao.Bebida_por_vendaDao;
import com.mycompany.pizzariaweb.modelo.dao.VendaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Bebida;
import com.mycompany.pizzariaweb.modelo.entidade.Bebida_por_venda;
import com.mycompany.pizzariaweb.modelo.entidade.Venda;
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
@WebServlet(WebConstantes.BASE_PATH + "/Bebida_por_vendaControlador")
public class Bebida_por_vendaControlador extends HttpServlet {

    private Bebida_por_venda objBebida_por_venda;
    private Bebida_por_vendaDao bebida_por_vendaDao;
    private Venda objVenda;
    private VendaDao vendaDao;
    private Bebida objBebida;
    private BebidaDao bebidaDao;
    String codigo="", venda_codPedido="", bebida_codBebida="", quantidade="", precoVenda="";

    @Override
    public void init() throws ServletException {
        bebida_por_vendaDao = new Bebida_por_vendaDao();
        objBebida_por_venda = new Bebida_por_venda();
        vendaDao = new VendaDao();
        objVenda = new Venda();
        bebidaDao = new BebidaDao();
        objBebida = new Bebida();
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
            bebida_codBebida = request.getParameter("bebida_codBebida");
            quantidade = request.getParameter("quantidade");
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
        objBebida_por_venda.getVenda_codPedido().setCodPedido(Integer.valueOf(venda_codPedido));
        objBebida_por_venda.getBebida_codBebida().setCodigoBebida(Integer.valueOf(bebida_codBebida));
        objBebida_por_venda.setQuantidade(Integer.valueOf(quantidade));
        objBebida_por_venda.setPrecoVenda(Double.valueOf(precoVenda));
        
        bebida_por_vendaDao.salvar(objBebida_por_venda);
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Bebida_por_venda> bebidas_por_vendas = bebida_por_vendaDao.buscarTodas();
        request.setAttribute("bebidas_por_vendas", bebidas_por_vendas);
        List<Venda> vendas = vendaDao.buscarTodas();
        request.setAttribute("vendas", vendas);
        List<Bebida> bebidas = bebidaDao.buscarTodas();
        request.setAttribute("bebidas", bebidas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroBebida_por_venda.jsp");
        dispatcher.forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("venda_codPedido", venda_codPedido);
        request.setAttribute("bebida_codBebida", bebida_codBebida);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("precoVenda", precoVenda);
        
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", codigo);
        request.setAttribute("venda_codPedido", venda_codPedido);
        request.setAttribute("bebida_codBebida", bebida_codBebida);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("precoVenda", precoVenda);
        
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        objBebida_por_venda.setCodigo(Integer.valueOf(codigo));
        objBebida_por_venda.getVenda_codPedido().setCodPedido(Integer.valueOf(venda_codPedido));
        objBebida_por_venda.getBebida_codBebida().setCodigoBebida(Integer.valueOf(bebida_codBebida));
        objBebida_por_venda.setQuantidade(Integer.valueOf(quantidade));
        objBebida_por_venda.setPrecoVenda(Double.valueOf(precoVenda));
        
        bebida_por_vendaDao.alterar(objBebida_por_venda);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        objBebida_por_venda.setCodigo(Integer.valueOf(codigo));
        bebida_por_vendaDao.excluir(objBebida_por_venda);
        encaminharParaPagina(request, response);
    }

    public void validaCampos() {
        if ((venda_codPedido==null) || (venda_codPedido.isEmpty()) || (bebida_codBebida==null) || (bebida_codBebida.isEmpty()) || (quantidade==null) || (quantidade.isEmpty()) || (precoVenda==null) || (precoVenda.isEmpty())) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");

        }
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigo", 0);
        request.setAttribute("venda_codPedido", "");
        request.setAttribute("bebida_codBebida", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("precoVenda", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }

}
