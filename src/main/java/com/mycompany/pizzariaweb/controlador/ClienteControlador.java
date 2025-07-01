/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariaweb.controlador;

import com.mycompany.pizzariaweb.modelo.dao.ClienteDao;
import com.mycompany.pizzariaweb.modelo.entidade.Cliente;
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
@WebServlet(WebConstantes.BASE_PATH+"/ClienteControlador")
public class ClienteControlador extends HttpServlet{ //herdar métodos necessários
    private Cliente objCliente;
    private ClienteDao clienteDao;
    String nomeCliente="", telefoneCliente="", cpfCliente="", bairroCliente="", ruaCliente="", numeroCliente="", emailCliente="", codigoCliente="";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        objCliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String opcao = request.getParameter("opcao");
            if(opcao==null || opcao.isEmpty()) opcao = "cadastrar";
            nomeCliente = request.getParameter("nomeCliente");
            telefoneCliente = request.getParameter("telefoneCliente");
            cpfCliente = request.getParameter("cpfCliente");
            bairroCliente = request.getParameter("bairroCliente");
            ruaCliente = request.getParameter("ruaCliente");
            numeroCliente = request.getParameter("numeroCliente");
            emailCliente = request.getParameter("emailCliente");
            codigoCliente = request.getParameter("codigoCliente");
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
        objCliente.setNomeCliente(nomeCliente);
        objCliente.setTelefoneCliente(telefoneCliente);
        objCliente.setCpfCliente(cpfCliente);
        objCliente.setBairroCliente(bairroCliente);
        objCliente.setRuaCliente(ruaCliente);
        objCliente.setNumeroCliente(numeroCliente);
        objCliente.setEmailCliente(emailCliente);
        clienteDao.salvar(objCliente);
        encaminharParaPagina(request, response);
    }
    
    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoCliente", codigoCliente);
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("telefoneCliente", telefoneCliente);
        request.setAttribute("cpfCliente", cpfCliente);
        request.setAttribute("bairroCliente", bairroCliente);
        request.setAttribute("ruaCliente", ruaCliente);
        request.setAttribute("numeroCliente", numeroCliente);
        request.setAttribute("emailCliente", emailCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("mensagem", "Edite os dados e clique no botão 'salvar'.");
        encaminharParaPagina(request, response);
    }
    
    private void excluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoCliente", codigoCliente);
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("telefoneCliente", telefoneCliente);
        request.setAttribute("cpfCliente", cpfCliente);
        request.setAttribute("bairroCliente", bairroCliente);
        request.setAttribute("ruaCliente", ruaCliente);
        request.setAttribute("numeroCliente", numeroCliente);
        request.setAttribute("emailCliente", emailCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("mensagem", "Clique no botão 'salvar' para excluir os dados.");
        encaminharParaPagina(request, response);
    }
    
    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        validaCampos();
        objCliente.setCodigoCliente(Integer.valueOf(codigoCliente));
        objCliente.setNomeCliente(nomeCliente);
        objCliente.setTelefoneCliente(telefoneCliente);
        objCliente.setCpfCliente(cpfCliente);
        objCliente.setBairroCliente(bairroCliente);
        objCliente.setRuaCliente(ruaCliente);
        objCliente.setNumeroCliente(numeroCliente);
        objCliente.setEmailCliente(emailCliente);
        clienteDao.alterar(objCliente);
        encaminharParaPagina(request, response);
    }
    
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        objCliente.setCodigoCliente(Integer.valueOf(codigoCliente));
        clienteDao.excluir(objCliente);
        encaminharParaPagina(request, response);
    }
    
    public void validaCampos(){
        if((nomeCliente==null) || (nomeCliente.isEmpty()) || (telefoneCliente==null) || (telefoneCliente.isEmpty()) || (bairroCliente==null) || (bairroCliente.isEmpty()) || (ruaCliente==null) || (ruaCliente.isEmpty()) || (numeroCliente==null) || (numeroCliente.isEmpty())){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes!");
        }
    }
    
    private void cancelar(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setAttribute("codigoCliente", 0);
        request.setAttribute("nomeCliente", "");
        request.setAttribute("telefoneCliente", "");
        request.setAttribute("cpfCliente", "");
        request.setAttribute("bairroCliente", "");
        request.setAttribute("ruaCliente", "");
        request.setAttribute("numeroCliente", "");
        request.setAttribute("opcao", "cadastrar");
        encaminharParaPagina(request, response);
    }
}
