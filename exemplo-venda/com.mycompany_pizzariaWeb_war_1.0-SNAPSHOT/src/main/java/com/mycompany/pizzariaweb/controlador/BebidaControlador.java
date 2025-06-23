/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.BebidaDao;
import com.mycompany.pizzariaweb.modelo.entidade.Bebida;
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
 * @author 14830919612
 */
@WebServlet(WebConstantes.BASE_PATH+"/BebidaControlador")
public class BebidaControlador extends HttpServlet{ //herdar métodos necessários
    private Bebida objBebida;
    private BebidaDao bebidaDao;
    String nomeBebida="", precoBebida="", quantidadeBebida="", codigoBebida="";

    @Override
    public void init() throws ServletException {
        bebidaDao = new BebidaDao();
        objBebida = new Bebida();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) opcao = "cadastrar";
            nomeBebida = request.getParameter("nomeBebida");
            precoBebida = request.getParameter("precoBebida");
            quantidadeBebida = request.getParameter("quantidadeBebida");
            codigoBebida = request.getParameter("codigoBebida");
            switch(opcao){
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
                    throw new IllegalArgumentException("Opção inválida!: " +opcao);
            }
        }catch(NumberFormatException e){
            response.getWriter().println(" Erro: um ou mais parâmetros não são números válidos! " +e);
        }catch(IllegalArgumentException e){
            response.getWriter().println(" Erro: valor do parâmetro ausente! " +e);
        }
    }
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        objBebida.setNomeBebida(nomeBebida);
        objBebida.setPrecoBebida(Double.valueOf(precoBebida));
        objBebida.setQuantidadeBebida(Integer.valueOf(quantidadeBebida));
        bebidaDao.salvar(objBebida);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Bebida> bebidas = bebidaDao.buscarTodas();
        request.setAttribute("bebidas", bebidas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroBebida.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoBebida", codigoBebida);
        request.setAttribute("nomeBebida", nomeBebida);
        request.setAttribute("precoBebida", precoBebida);
        request.setAttribute("quantidadeBebida", quantidadeBebida);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoBebida", codigoBebida);
        request.setAttribute("nomeBebida", nomeBebida);
        request.setAttribute("precoBebida", precoBebida);
        request.setAttribute("quantidadeBebida", quantidadeBebida);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        objBebida.setCodigoBebida(Integer.valueOf(codigoBebida));
        objBebida.setNomeBebida(nomeBebida);
        objBebida.setPrecoBebida(Double.valueOf(precoBebida));
        objBebida.setQuantidadeBebida(Integer.valueOf(quantidadeBebida));
        bebidaDao.alterar(objBebida);
        encaminharParaPagina(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        objBebida.setCodigoBebida(Integer.valueOf(codigoBebida));
        bebidaDao.excluir(objBebida);
        encaminharParaPagina(request, response);
    }
    
    public void validaCampos(){
        if((nomeBebida==null) || (nomeBebida.isEmpty()) || (precoBebida==null) || (precoBebida.isEmpty()) || (quantidadeBebida==null) || (quantidadeBebida.isEmpty())){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes!");
        }
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoBebida", 0);
        request.setAttribute("nomeBebida", "");
        request.setAttribute("precoBebida", "");
        request.setAttribute("quantidadeBebida", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}
