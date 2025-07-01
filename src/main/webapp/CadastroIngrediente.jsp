<%-- 
    Document   : Cadastro
    Created on : 15 de jul de 2024, 13:20:59
    Author     : 14830919612
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1 class="tit">Cadastro Ingrediente</h1>
        <div class="cadastro">
            
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/IngredienteControlador">
                <p> <label>Nome:</label>
                    <input type="text" name="nomeIngrediente" value="${nomeIngrediente}" size="40" required class="form"/>
                </p>
                <p> <label>Preço:</label>
                    <input type="text" name="precoIngrediente" value="${precoIngrediente}" size="40" required class="form"/>
                </p>
                <p> <label>Quantidade no estoque:</label>
                    <input type="text" name="quantidadeEstoqueIngrediente" value="${quantidadeEstoqueIngrediente}" size="40" required class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoIngrediente" value="${codigoIngrediente}">
                
                    <table border="0">
                        <tr>
                            <td>
                                <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                            </td>
                </form>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/IngredienteControlador">
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
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Quantidade no Estoque</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="ingrediente" items="${ingredientes}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${ingrediente.codigoIngrediente}</td>
                            <td class="td">${ingrediente.nomeIngrediente}</td>
                            <td class="td">${ingrediente.precoIngrediente}</td>
                            <td class="td">${ingrediente.quantidadeEstoqueIngrediente}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/IngredienteControlador">
                                    <input type="hidden" name="codigoIngrediente" value="${ingrediente.codigoIngrediente}">
                                    <input type="hidden" name="nomeIngrediente" value="${ingrediente.nomeIngrediente}">
                                    <input type="hidden" name="precoIngrediente" value="${ingrediente.precoIngrediente}"> 
                                    <input type="hidden" name="quantidadeEstoqueIngrediente" value="${ingrediente.quantidadeEstoqueIngrediente}"> 
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/IngredienteControlador">
                                    <input type="hidden" name="codigoIngrediente" value="${ingrediente.codigoIngrediente}">
                                    <input type="hidden" name="nomeIngrediente" value="${ingrediente.nomeIngrediente}">
                                    <input type="hidden" name="precoIngrediente" value="${ingrediente.precoIngrediente}"> 
                                    <input type="hidden" name="quantidadeEstoqueIngrediente" value="${ingrediente.quantidadeEstoqueIngrediente}"> 
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
