/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.FornecedorDao;
import com.mycompany.pizzariaweb.modelo.entidade.Fornecedor;
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
 * @author 16658144621
 */
@WebServlet(WebConstantes.BASE_PATH+"/FornecedorControlador")
public class FornecedorControlador extends HttpServlet{
    private Fornecedor objFornecedor;
    private FornecedorDao fornecedorDao;
    String  codigoFornecedor="", nomeFornecedor="", telefoneFornecedor="", emailFornecedor="", cpfFornecedor="";

    @Override
    public void init() throws ServletException {
        fornecedorDao = new FornecedorDao();
        objFornecedor = new Fornecedor();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) {opcao = "cadastrar";}
            nomeFornecedor = request.getParameter("nomeFornecedor");
            telefoneFornecedor = request.getParameter("telefoneFornecedor");
            emailFornecedor = request.getParameter("emailFornecedor");
            cpfFornecedor = request.getParameter("cpfFornecedor");
            codigoFornecedor = request.getParameter("codigoFornecedor");
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
    
    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        validaCampos();
        objFornecedor.setNomeFornecedor(nomeFornecedor);
        objFornecedor.setTelefoneFornecedor(telefoneFornecedor);
        objFornecedor.setEmailFornecedor(emailFornecedor);
        objFornecedor.setCpfFornecedor(cpfFornecedor);
        fornecedorDao.salvar(objFornecedor);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        List<Fornecedor> fornecedores = fornecedorDao.buscarTodas();
        request.setAttribute("fornecedores", fornecedores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFornecedor.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("codigoFornecedor", codigoFornecedor);
        request.setAttribute("nomeFornecedor", nomeFornecedor);
        request.setAttribute("telefoneFornecedor", telefoneFornecedor);
        request.setAttribute("emailFornecedor",  emailFornecedor);
        request.setAttribute("cpfFornecedor", cpfFornecedor);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("codigoFornecedor", codigoFornecedor);
        request.setAttribute("nomeFornecedor", nomeFornecedor);
        request.setAttribute("telefoneFornecedor", telefoneFornecedor);
        request.setAttribute("emailFornecedor",  emailFornecedor);
        request.setAttribute("cpfFornecedor", cpfFornecedor);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{   
        validaCampos();
        objFornecedor.setCodigoFornecedor(Integer.valueOf(codigoFornecedor));
        objFornecedor.setNomeFornecedor(nomeFornecedor);
        objFornecedor.setTelefoneFornecedor(telefoneFornecedor);
        objFornecedor.setEmailFornecedor(emailFornecedor);
        objFornecedor.setCpfFornecedor(cpfFornecedor);
        fornecedorDao.alterar(objFornecedor);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{   
        objFornecedor.setCodigoFornecedor(Integer.valueOf(codigoFornecedor));
        fornecedorDao.excluir(objFornecedor);
        encaminharParaPagina(request, response);
    }
   public void validaCampos(){
       if((nomeFornecedor==null)||(nomeFornecedor.isEmpty()) || (telefoneFornecedor==null)||(telefoneFornecedor.isEmpty())){
           throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
    }
  }
   private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       request.setAttribute("codigoFornecedor", 0);
       request.setAttribute("nomeFornecedor", "");
       request.setAttribute("telefoneFornecedor", "");
       request.setAttribute("emailFornecedor", "");
       request.setAttribute("cpfFornecedor", "");
       request.setAttribute("opcao", "cadastrar");
       encaminharParaPagina(request, response);
   } 
   
}
