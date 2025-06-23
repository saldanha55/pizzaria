/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.entidade.Funcionario;
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
@WebServlet(WebConstantes.BASE_PATH+"/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet{
    private Funcionario objFuncionario;
    private FuncionarioDao funcionarioDao;
    String  codigoFuncionario="", nomeFuncionario="", cpfFuncionario="", salarioFuncionario="", cargoFuncionario="", bairroFuncionario="", ruaFuncionario="", numeroFuncionario="", telefoneFuncionario="", emailFuncionario="";

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        objFuncionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) opcao = "cadastrar";
            nomeFuncionario = request.getParameter("nomeFuncionario");
            cpfFuncionario = request.getParameter("cpfFuncionario");
            salarioFuncionario = request.getParameter("salarioFuncionario");
            cargoFuncionario = request.getParameter("cargoFuncionario");
            bairroFuncionario = request.getParameter("bairroFuncionario");
            ruaFuncionario = request.getParameter("ruaFuncionario");
            numeroFuncionario = request.getParameter("numeroFuncionario");
            telefoneFuncionario = request.getParameter("telefoneFuncionario");
            emailFuncionario = request.getParameter("emailFuncionario");
            codigoFuncionario = request.getParameter("codigoFuncionario");
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
        objFuncionario.setNomeFuncionario(nomeFuncionario);
        objFuncionario.setCpfFuncionario(cpfFuncionario);
        objFuncionario.setSalarioFuncionario(Double.valueOf(salarioFuncionario));
        objFuncionario.setCargoFuncionario(cargoFuncionario);
        objFuncionario.setBairroFuncionario(bairroFuncionario);
        objFuncionario.setRuaFuncionario(ruaFuncionario);
        objFuncionario.setNumeroFuncionario(numeroFuncionario);
        objFuncionario.setTelefoneFuncionario(telefoneFuncionario);
        objFuncionario.setEmailFuncionario(emailFuncionario);
        funcionarioDao.salvar(objFuncionario);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        System.out.println(funcionarios);
        request.setAttribute("funcionarios", funcionarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("codigoFuncionario", codigoFuncionario);
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cpfFuncionario", cpfFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("cargoFuncionario", cargoFuncionario);
        request.setAttribute("bairroFuncionario", bairroFuncionario);
        request.setAttribute("ruaFuncionario", ruaFuncionario);
        request.setAttribute("numeroFuncionario", numeroFuncionario);
        request.setAttribute("telefoneFuncionario", telefoneFuncionario);
        request.setAttribute("emailFuncionario",  emailFuncionario);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("codigoFuncionario", codigoFuncionario);
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("cpfFuncionario", cpfFuncionario);
        request.setAttribute("salarioFuncionario", salarioFuncionario);
        request.setAttribute("cargoFuncionario", cargoFuncionario);
        request.setAttribute("bairroFuncionario", bairroFuncionario);
        request.setAttribute("ruaFuncionario", ruaFuncionario);
        request.setAttribute("numeroFuncionario", numeroFuncionario);
        request.setAttribute("telefoneFuncionario", telefoneFuncionario);
        request.setAttribute("emailFuncionario",  emailFuncionario);
    request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão salvar para excluir os dados");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{   
        validaCampos();
        objFuncionario.setCodigoFuncionario(Integer.valueOf(codigoFuncionario));
        objFuncionario.setNomeFuncionario(nomeFuncionario);
        objFuncionario.setCpfFuncionario(cpfFuncionario);
        objFuncionario.setSalarioFuncionario(Double.valueOf(salarioFuncionario));
        objFuncionario.setCargoFuncionario(cargoFuncionario);
        objFuncionario.setBairroFuncionario(bairroFuncionario);
        objFuncionario.setRuaFuncionario(ruaFuncionario);
        objFuncionario.setNumeroFuncionario(numeroFuncionario);
        objFuncionario.setTelefoneFuncionario(telefoneFuncionario);
        objFuncionario.setEmailFuncionario(emailFuncionario);
        funcionarioDao.alterar(objFuncionario);
        encaminharParaPagina(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{   
        objFuncionario.setCodigoFuncionario(Integer.valueOf(codigoFuncionario));
        funcionarioDao.excluir(objFuncionario);
        encaminharParaPagina(request, response);
    }
   public void validaCampos(){
       if((nomeFuncionario==null)||(nomeFuncionario.isEmpty()) || (cpfFuncionario==null)||(cpfFuncionario.isEmpty()) || (salarioFuncionario==null)||(salarioFuncionario.isEmpty()) || (cargoFuncionario==null)||(cargoFuncionario.isEmpty()) || (bairroFuncionario==null)||(bairroFuncionario.isEmpty()) || (ruaFuncionario==null)||(ruaFuncionario.isEmpty()) || (numeroFuncionario==null)||(numeroFuncionario.isEmpty()) || (telefoneFuncionario==null)||(telefoneFuncionario.isEmpty()) || (emailFuncionario==null)||(emailFuncionario.isEmpty())){
           throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
    }
  }
   private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       request.setAttribute("codigoFuncionario", 0);
       request.setAttribute("nomeFuncionario", "");
       request.setAttribute("cpfFuncionario", "");
       request.setAttribute("salarioFuncionario", "");
       request.setAttribute("cargoFuncionario", "");
       request.setAttribute("bairroFuncionario", "");
       request.setAttribute("ruaFuncionario", "");
       request.setAttribute("numeroFuncionario", "");
       request.setAttribute("telefoneFuncionario", "");
       request.setAttribute("emailFuncionario", "");
       request.setAttribute("opcao", "cadastrar");
       encaminharParaPagina(request, response);
   } 
   
}
