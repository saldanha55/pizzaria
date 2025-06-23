<%-- 
    Document   : CadastroCidade
    Created on : 15 de jul. de 2024, 13:21:04
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
        <h1 class="tit">Cadastro Pizza por Venda</h1>
        <div class="cadastro">
            
            <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Pizza_por_vendaControlador" method="get" >
                <p> <label>Código da Venda:</label>
                    <select name="venda_codPedido" class="form">
                        <c:forEach var="venda" items="${vendas}">
                            <c:choose>
                                <c:when test="${venda.codPedido eq venda_codPedido}">
                          <option selected value="${venda.codPedido}">${venda.codPedido}</option> <!--Selecionar o código da venda-->
                                </c:when>
                                <c:otherwise>
                          <option value="${venda.codPedido}">${venda.codPedido}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p> <label>Pizza:</label>
                    <select name="pizza_codPizza" class="form">
                        <c:forEach var="pizza" items="${pizzas}">
                            <c:choose>
                                <c:when test="${pizza.codPizza eq pizza_codPizza}">
                          <option selected value="${pizza.codPizza}">${pizza.nome}</option>
                                </c:when>
                                <c:otherwise>
                          <option value="${pizza.codPizza}">${pizza.nome}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p> <label>Quantidade:</label>
                    <input type="text" name="quantidade" value="${quantidade}" size="40" required class="form"/>
                </p>
                <p> <label>Tamanho:</label>
                    <select name="tamanho" values="${tamanho}" required class="form">
                        <option value="Pequena">Pequena</option>
                        <option value="Media">Média</option>
                        <option value="Grande">Grande</option>
                    </select>
                </p>
                <p> <label>Preço da Venda:</label>
                    <input type="text" name="precoVenda" value="${precoVenda}" size="40" required class="form"/>
                </p>
                
                <input type="hidden" name="opcao" value="${opcao}" >
                <input type="hidden" name="codigo" value="${codigo}" >
                
                <table border="0">
                    <tr>
                        <td>
                            <input class="butaoSalvar" type="submit" value="Salvar" name="Salvar" />
                        </td>
                        </form>
                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Pizza_por_vendaControlador" method="get" >

                            <input type="hidden" name="opcao" value="cancelar" >
                        <td>   
                        <input class="butaoCancelar" type="submit" value="Cancelar" name="Cancelar" />
                         </td>
        
                    </form>
                    </div>
                    <!--/tr-->
                </table>
                <p> <label> ${mensagem}</label><p>
            </div>
            
            <div class="tabela">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Código da Venda</th>
                            <th>Pizza</th>
                            <th>Quantidade</th>
                            <th>Tamanho</th>
                            <th>Preço da Venda</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>

                    <c:forEach var="pizza_por_venda" items="${pizzas_por_vendas}">
                        <tbody class="tbody">
                            <tr>
                                <td class="td">${pizza_por_venda.codigo}</td>
                                <td class="td">${pizza_por_venda.venda_codPedido.codPedido}</td>
                                <td class="td">${pizza_por_venda.pizza_codPizza.nome}</td>
                                <td class="td">${pizza_por_venda.quantidade}</td>
                                <td class="td">${pizza_por_venda.tamanho}</td>
                                <td class="td">${pizza_por_venda.precoVenda}</td>

                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Pizza_por_vendaControlador" >
                                        <input type="hidden" name="codigo" value="${pizza_por_venda.codigo}">
                                        <input type="hidden" name="venda_codPedido" value="${pizza_por_venda.venda_codPedido.codPedido}">
                                        <input type="hidden" name="pizza_codPizza" value="${pizza_por_venda.pizza_codPizza.codPizza}">
                                        <input type="hidden" name="quantidade" value="${pizza_por_venda.quantidade}">
                                        <input type="hidden" name="tamanho" value="${pizza_por_venda.tamanho}">
                                        <input type="hidden" name="precoVenda" value="${pizza_por_venda.precoVenda}">

                                        <input type="hidden" name="opcao" value="editar">
                                        <button class="butaoEditar" type="submit">Editar</button>
                                    </form> 
                                </td>
                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Pizza_por_vendaControlador" >
                                        <input type="hidden" name="codigo" value="${pizza_por_venda.codigo}">
                                        <input type="hidden" name="venda_codPedido" value="${pizza_por_venda.venda_codPedido.codPedido}">
                                        <input type="hidden" name="pizza_codPizza" value="${pizza_por_venda.pizza_codPizza.codPizza}">
                                        <input type="hidden" name="quantidade" value="${pizza_por_venda.quantidade}">
                                        <input type="hidden" name="tamanho" value="${pizza_por_venda.tamanho}">
                                        <input type="hidden" name="precoVenda" value="${pizza_por_venda.precoVenda}">

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
