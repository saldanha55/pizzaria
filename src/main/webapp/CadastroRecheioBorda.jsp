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

        <h1 class="tit">Cadastro Recheio Borda</h1>
        <div class="cadastro">
            
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/RecheioBordaControlador">
                <p> <label>Sabor:</label>
                    <input type="text" name="saborRecheioBorda" value="${saborRecheioBorda}" size="40" required class="form"/>
                </p>
                <p> <label>Preço:</label>
                    <input type="text" name="precoRecheioBorda" value="${precoRecheioBorda}" size="40" required class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoRecheioBorda" value="${codigoRecheioBorda}">
                
                    <table border="0">
                        <tr>
                            <td>
                                <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                            </td>
                </form>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/RecheioBordaControlador">
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
            <table border="1">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Sabor</th>
                        <th>Preço</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="recheioBorda" items="${recheioBordas}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${recheioBorda.codigoRecheioBorda}</td>
                            <td class="td">${recheioBorda.saborRecheioBorda}</td>
                            <td class="td">${recheioBorda.precoRecheioBorda}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/RecheioBordaControlador">
                                    <input type="hidden" name="codigoRecheioBorda" value="${recheioBorda.codigoRecheioBorda}">
                                    <input type="hidden" name="saborRecheioBorda" value="${recheioBorda.saborRecheioBorda}">
                                    <input type="hidden" name="precoRecheioBorda" value="${recheioBorda.precoRecheioBorda}"> 
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/RecheioBordaControlador">
                                    <input type="hidden" name="codigoRecheioBorda" value="${recheioBorda.codigoRecheioBorda}">
                                    <input type="hidden" name="saborRecheioBorda" value="${recheioBorda.saborRecheioBorda}">
                                    <input type="hidden" name="precoRecheioBorda" value="${recheioBorda.precoRecheioBorda}"> 
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
