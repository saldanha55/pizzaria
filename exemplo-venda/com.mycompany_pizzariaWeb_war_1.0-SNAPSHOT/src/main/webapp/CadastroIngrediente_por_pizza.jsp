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
        <h1 class="tit">Cadastro Ingrediente por Pizza</h1>
        <div class="cadastro">
            
            <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Ingrediente_por_pizzaControlador" method="get" >
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
                <p> <label>Ingrediente:</label>
                    <select name="ingrediente_codIngrediente" class="form">
                        <c:forEach var="ingrediente" items="${ingredientes}">
                            <c:choose>
                                <c:when test="${ingrediente.codigoIngrediente eq ingrediente_codIngrediente}">
                          <option selected value="${ingrediente.codigoIngrediente}">${ingrediente.nomeIngrediente}</option>
                                </c:when>
                                <c:otherwise>
                          <option value="${ingrediente.codigoIngrediente}">${ingrediente.nomeIngrediente}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p> <label>Quantidade de Ingrediente Nesta Pizza:</label>
                    <input type="text" name="quantIngrediente" value="${quantIngrediente}" size="40" required class="form"/>
                </p>
                
                <input type="hidden" name="opcao" value="${opcao}" >
                <input type="hidden" name="codigo" value="${codigo}" >
                
                <table border="0">
                    <tr>
                        <td>
                            <input class="butaoSalvar" type="submit" value="Salvar" name="Salvar" />
                        </td>
                        </form>
                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Ingrediente_por_pizzaControlador" method="get" >

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
                            <th>Pizza</th>
                            <th>Ingrediente</th>
                            <th>Quantidade de Ingrediente Nesta Pizza</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>

                    <c:forEach var="ingrediente_por_pizza" items="${ingredientes_por_pizzas}">
                        <tbody class="tbody">
                            <tr>
                                <td class="td">${ingrediente_por_pizza.codigo}</td>
                                <td class="td">${ingrediente_por_pizza.pizza_codPizza.nome}</td>
                                <td class="td">${ingrediente_por_pizza.ingrediente_codIngrediente.nomeIngrediente}</td>
                                <td class="td">${ingrediente_por_pizza.quantIngrediente}</td>

                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Ingrediente_por_pizzaControlador" >
                                        <input type="hidden" name="codigo" value="${ingrediente_por_pizza.codigo}">
                                        <input type="hidden" name="pizza_codPizza" value="${Ingrediente_por_pizza.pizza_codPizza.codPizza}">
                                        <input type="hidden" name="ingrediente_codIngrediente" value="${Ingrediente_por_pizza.ingrediente_codIngrediente.codigoIngrediente}">
                                        <input type="hidden" name="quantIngrediente" value="${ingrediente_por_pizza.quantIngrediente}">

                                        <input type="hidden" name="opcao" value="editar">
                                        <button class="butaoEditar" type="submit">Editar</button>
                                    </form> 
                                </td>
                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Ingrediente_por_pizzaControlador" >
                                        <input type="hidden" name="codigo" value="${ingrediente_por_pizza.codigo}">
                                        <input type="hidden" name="pizza_codPizza" value="${Ingrediente_por_pizza.pizza_codPizza.codPizza}">
                                        <input type="hidden" name="ingrediente_codIngrediente" value="${Ingrediente_por_pizza.ingrediente_codIngrediente.codigoIngrediente}">
                                        <input type="hidden" name="quantIngrediente" value="${ingrediente_por_pizza.quantIngrediente}">

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
