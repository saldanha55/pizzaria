/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.IngredienteDao;
import com.mycompany.pizzariaweb.modelo.entidade.Ingrediente;
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
@WebServlet(WebConstantes.BASE_PATH+"/IngredienteControlador")
public class IngredienteControlador extends HttpServlet{ //herdar métodos necessários
    private Ingrediente objIngrediente;
    private IngredienteDao ingredienteDao;
    String nomeIngrediente="", precoIngrediente="", quantidadeEstoqueIngrediente="", codigoIngrediente="";

    @Override
    public void init() throws ServletException {
        ingredienteDao = new IngredienteDao();
        objIngrediente = new Ingrediente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) opcao = "cadastrar";
            nomeIngrediente = request.getParameter("nomeIngrediente");
            precoIngrediente = request.getParameter("precoIngrediente");
            quantidadeEstoqueIngrediente = request.getParameter("quantidadeEstoqueIngrediente");
            codigoIngrediente = request.getParameter("codigoIngrediente");
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
        objIngrediente.setNomeIngrediente(nomeIngrediente);
        objIngrediente.setPrecoIngrediente(Double.valueOf(precoIngrediente));
        objIngrediente.setQuantidadeEstoqueIngrediente(Float.valueOf(quantidadeEstoqueIngrediente));
        ingredienteDao.salvar(objIngrediente);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Ingrediente> ingredientes = ingredienteDao.buscarTodas();
        request.setAttribute("ingredientes", ingredientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroIngrediente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoIngrediente", codigoIngrediente);
        request.setAttribute("nomeIngrediente", nomeIngrediente);
        request.setAttribute("precoIngrediente", precoIngrediente);
        request.setAttribute("quantidadeEstoqueIngrediente", quantidadeEstoqueIngrediente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoIngrediente", codigoIngrediente);
        request.setAttribute("nomeIngrediente", nomeIngrediente);
        request.setAttribute("precoIngrediente", precoIngrediente);
        request.setAttribute("quantidadeEstoqueIngrediente", quantidadeEstoqueIngrediente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        objIngrediente.setCodigoIngrediente(Integer.valueOf(codigoIngrediente));
        objIngrediente.setNomeIngrediente(nomeIngrediente);
        objIngrediente.setPrecoIngrediente(Double.valueOf(precoIngrediente));
        objIngrediente.setQuantidadeEstoqueIngrediente(Float.valueOf(quantidadeEstoqueIngrediente));
        ingredienteDao.alterar(objIngrediente);
        encaminharParaPagina(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        objIngrediente.setCodigoIngrediente(Integer.valueOf(codigoIngrediente));
        ingredienteDao.excluir(objIngrediente);
        encaminharParaPagina(request, response);
    }
    
    public void validaCampos(){
        if((nomeIngrediente==null) || (nomeIngrediente.isEmpty()) || (precoIngrediente==null) || (precoIngrediente.isEmpty()) || (quantidadeEstoqueIngrediente==null) || (quantidadeEstoqueIngrediente.isEmpty())){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes!");
        }
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoIngrediente", 0);
        request.setAttribute("nomeIngrediente", "");
        request.setAttribute("precoIngrediente", "");
        request.setAttribute("quantidadeEstoqueIngrediente", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}
