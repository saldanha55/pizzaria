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
        <h1 class="tit">Cadastro Pizza</h1>
        <div class="cadastro">

            <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PizzaControlador" method="get" >
                <p> <label>Nome:</label>
                    <input type="text" name="nome" value="${nome}" size="40" required class="form"/>
                </p>
                <p> <label>Preço Base:</label>
                    <input type="text" name="precoBase" value="${precoBase}" size="40" required class="form"/>
                </p>

                <tr>
                    <td><label for="tamanho">Tamanho:</label></td>
                    <td>
                        <%-- O 'select' verifica qual opção deve vir pré-selecionada --%>
                        <select name="tamanho" id="tamanho" required>
                            <option value="">Selecione...</option>
                            <option value="Pequena" ${tamanho == 'Pequena' ? 'selected' : ''}>Pequena</option>
                            <option value="Média"   ${tamanho == 'Média'   ? 'selected' : ''}>Média</option>
                            <option value="Grande"  ${tamanho == 'Grande'  ? 'selected' : ''}>Grande</option>
                        </select>
                        <%-- Se estiver desabilitado, o valor não é enviado. Usamos um input hidden para garantir o envio. --%>
                    </td>
                </tr>

                <p> <label>Recheio da Borda:</label>
                    <select name="pizza_borda" class="form">
                        <c:forEach var="recheioBorda" items="${recheioBordas}">
                            <c:choose>
                                <c:when test="${recheioBorda.codigoRecheioBorda eq pizza_borda}">
                                    <option selected value="${recheioBorda.codigoRecheioBorda}">${recheioBorda.saborRecheioBorda}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${recheioBorda.codigoRecheioBorda}">${recheioBorda.saborRecheioBorda}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>

                <input type="hidden" name="opcao" value="${opcao}" >
                <input type="hidden" name="codPizza" value="${codPizza}" >

                <table border="0">
                    <tr>
                        <td>
                            <input class="butaoSalvar" type="submit" value="Salvar" name="Salvar" />
                        </td>
                        </form>
                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PizzaControlador" method="get" >

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
                                <th>Nome</th>
                                <th>Preço Base</th>
                                <th>Tamanho</th>
                                <th>Recheio da Borda</th>
                                <th>Alterar</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>

                        <c:forEach var="pizza" items="${pizzas}">
                            <tbody class="tbody">
                                <tr>
                                    <td class="td">${pizza.codPizza}</td>
                                    <td class="td">${pizza.nome}</td>
                                    <td class="td">${pizza.precoBase}</td>
                                    <td class="td">${pizza.tamanho}</td>
                                    <td class="td">${pizza.pizza_borda.saborRecheioBorda}</td>

                                    <td class="td">
                                        <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PizzaControlador" >
                                            <input type="hidden" name="codPizza" value="${pizza.codPizza}">
                                            <input type="hidden" name="nome" value="${pizza.nome}">
                                            <input type="hidden" name="precoBase" value="${pizza.precoBase}">
                                            <input type="hidden" name="tamanho" value="${pizza.tamanho}">
                                            <input type="hidden" name="pizza_borda" value="${pizza.pizza_borda.codigoRecheioBorda}">

                                            <input type="hidden" name="opcao" value="editar">
                                            <button class="butaoEditar" type="submit">Editar</button>
                                        </form> 
                                    </td>
                                    <td class="td">
                                        <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/PizzaControlador" >
                                            <input type="hidden" name="codPizza" value="${pizza.codPizza}">
                                            <input type="hidden" name="nome" value="${pizza.nome}">
                                            <input type="hidden" name="precoBase" value="${pizza.precoBase}">
                                            <input type="hidden" name="tamanho" value="${pizza.tamanho}">
                                            <input type="hidden" name="pizza_borda" value="${pizza.pizza_borda.codigoRecheioBorda}">

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
