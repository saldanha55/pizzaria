/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.RecheioBordaDao;
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
 * @author 14830919612
 */
@WebServlet(WebConstantes.BASE_PATH+"/RecheioBordaControlador")
public class RecheioBordaControlador extends HttpServlet{ //herdar métodos necessários
    private RecheioBorda objRecheioBorda;
    private RecheioBordaDao recheioBordaDao;
    String saborRecheioBorda="", precoRecheioBorda="", codigoRecheioBorda="";

    @Override
    public void init() throws ServletException {
        recheioBordaDao = new RecheioBordaDao();
        objRecheioBorda = new RecheioBorda();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) opcao = "cadastrar";
            saborRecheioBorda = request.getParameter("saborRecheioBorda");
            precoRecheioBorda = request.getParameter("precoRecheioBorda");
            codigoRecheioBorda = request.getParameter("codigoRecheioBorda");
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
        objRecheioBorda.setSaborRecheioBorda(saborRecheioBorda);
        objRecheioBorda.setPrecoRecheioBorda(Double.valueOf(precoRecheioBorda));
        recheioBordaDao.salvar(objRecheioBorda);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<RecheioBorda> recheioBordas = recheioBordaDao.buscarTodas();
        request.setAttribute("recheioBordas", recheioBordas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroRecheioBorda.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoRecheioBorda", codigoRecheioBorda);
        request.setAttribute("saborRecheioBorda", saborRecheioBorda);
        request.setAttribute("precoRecheioBorda", precoRecheioBorda);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoRecheioBorda", codigoRecheioBorda);
        request.setAttribute("saborRecheioBorda", saborRecheioBorda);
        request.setAttribute("precoRecheioBorda", precoRecheioBorda);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        objRecheioBorda.setCodigoRecheioBorda(Integer.valueOf(codigoRecheioBorda));
        objRecheioBorda.setSaborRecheioBorda(saborRecheioBorda);
        objRecheioBorda.setPrecoRecheioBorda(Double.valueOf(precoRecheioBorda));
        recheioBordaDao.alterar(objRecheioBorda);
        encaminharParaPagina(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        objRecheioBorda.setCodigoRecheioBorda(Integer.valueOf(codigoRecheioBorda));
        recheioBordaDao.excluir(objRecheioBorda);
        encaminharParaPagina(request, response);
    }
    
    public void validaCampos(){
        if((saborRecheioBorda==null) || (saborRecheioBorda.isEmpty()) || (precoRecheioBorda==null) || (precoRecheioBorda.isEmpty())){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes!");
        }
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoRecheioBorda", 0);
        request.setAttribute("saborRecheioBorda", "");
        request.setAttribute("precoRecheioBorda", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}
