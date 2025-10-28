<%-- 
    Document   : Cadastro
    Created on : 15 de jul de 2024, 13:20:59
    Author     : 14830919612
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
               

        <h1 class="tit">Cadastro Bebida</h1>
        <div class="cadastro">
            
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/BebidaControlador">
                <p> <label>Nome:</label>
                    <input type="text" name="nomeBebida" value="${nomeBebida}" size="40" required class="form"/>
                </p>
                <p> <label>Preço:</label>
                    <input type="number" name="precoBebida" value="${precoBebida}" size="40" required class="form"/>
                </p>
                <p> <label>Quantidade:</label>
                    <input type="number" name="quantidadeBebida" value="${quantidadeBebida}" size="40" required class="form"/>
                </p>
                
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoBebida" value="${codigoBebida}">
                
                    <table border="0">
                        <tr>
                            <td>
                                <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                            </td>
                </form>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/BebidaControlador">
                    <input type="hidden" name="opcao" value="cancelar">
                    <td>
                        <input class="butaoCancelar" type="submit" name="Cancelar" value="Cancelar" />
                    </td>
                </form>
                        </div>
                    </table>
               
                
            <p><label> ${mensagem}</label></p>
        </div>
        
        <div class="tabela">
            <table border="1, solid">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Quantidade</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="bebida" items="${bebidas}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${bebida.codigoBebida}</td>
                            <td class="td">${bebida.nomeBebida}</td>
                            <td class="td">${bebida.precoBebida}</td>
                            <td class="td">${bebida.quantidadeBebida}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/BebidaControlador">
                                    <input type="hidden" name="codigoBebida" value="${bebida.codigoBebida}">
                                    <input type="hidden" name="nomeBebida" value="${bebida.nomeBebida}">
                                    <input type="hidden" name="precoBebida" value="${bebida.precoBebida}"> 
                                    <input type="hidden" name="quantidadeBebida" value="${bebida.quantidadeBebida}"> 
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/BebidaControlador">
                                    <input type="hidden" name="codigoBebida" value="${bebida.codigoBebida}">
                                    <input type="hidden" name="nomeBebida" value="${bebida.nomeBebida}">
                                    <input type="hidden" name="precoBebida" value="${bebida.precoBebida}"> 
                                    <input type="hidden" name="quantidadeBebida" value="${bebida.quantidadeBebida}"> 
                                    <input type="hidden" name="opcao" value="excluir">
                                    <button class="butaoExcluir" type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
