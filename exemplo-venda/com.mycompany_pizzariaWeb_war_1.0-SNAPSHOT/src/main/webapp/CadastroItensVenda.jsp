<%-- 
    Document   : CadastroCidade
    Created on : 15 de jul. de 2024, 13:21:04
    Author     : tulio

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="Menu.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="novaVenda"  style="margin-left: 20px;margin-bottom: 20px;">
            
            <h1>Venda</h1>
            <form name="novaVenda"  action="${pageContext.request.contextPath}${URL_BASE}/VendaControlador">
                <p> <label>Atendente:</label>
                    <select name="funcionario_codFuncionario" class="form">
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <c:choose>
                                <c:when test="${funcionario.codigoFuncionario eq funcionario_codFuncionario}">
                                    <option selected value="${funcionario.codigoFuncionario}">${funcionario.nomeFuncionario}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${funcionario.codigoFuncionario}">${funcionario.nomeFuncionario}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>

                <p> <label>Data:</label>
                    <input type="date" name="data" value="${data}" size="40" required class="form"/>
                </p>
                <p> <label>Cliente:</label>
                    <select name="cliente_codCliente" class="form">
                        <c:forEach var="cliente" items="${clientes}">
                            <c:choose>
                                <c:when test="${cliente.codigoCliente eq cliente_codCliente}">
                                    <option selected value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cliente.codigoCliente}">${cliente.nomeCliente}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                
                <input type="hidden" name="codPedido" value="${codPedido}">
                <input type="submit" name="opcao" value="Cadastrar"> <!-- Para editar um pedido/venda -->
                <!-- Para poder fazer um novo pedido/venda -->
                <input type="submit" name="opcao" value="Finalizar" ${codPedido == '0' ? 'disabled' : ''}>
                
                <!-- Para apagar um pedido q começou a ser feito pedido/venda -->
                <input type="submit" name="opcao" value="Cancelar">
            </form>
        </div>

        <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ItensVendaControlador">
            <input type="hidden" name="codPedido" value="${codPedido}">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-5">
                    <h2 style="margin-left: 20px;">Lista de Produtos:</h2>
                    <select name="pizzaVenda" class="form" multiple="multiple" style="margin: 20px; height: 150px; width: -webkit-fill-available;">
                        <c:forEach var="pizza" items="${pizzas}">   
                            <option value="${pizza.codPizza}">${pizza.nome} - R$ ${pizza.precoBase}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-2">
                    <label for="">Quantidade</label>
                    <input type="number" name="quantidade" value="1" size="4">
                    <input type="submit" name="opcao" value="Vender">
                </div>

                <div class="col-md-5">
                    <h2 style="margin-left: 20px;">Venda:</h2>
                    <select name="itemVendido" class="form" multiple="multiple" style="margin: 20px; height: 150px; width: -webkit-fill-available;">
                        <c:forEach var="itensVenda" items="${itensVendas}">
                            <option value="${itensVenda.codItensVenda}">${itensVenda.objItens_codPizza.nome} - R$ ${itensVenda.valor}</option>
                        </c:forEach>
                    </select>
                    <input class="butaoCancelar" type="submit" value="Cancelar" name="opcao" />
                </div>
            </div>
        </form>
    </body>
</html>
