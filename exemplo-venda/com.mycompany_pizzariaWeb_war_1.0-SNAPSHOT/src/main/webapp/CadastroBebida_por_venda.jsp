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
        <h1 class="tit">Cadastro Bebida por Venda</h1>
        <div class="cadastro">
            
            <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Bebida_por_vendaControlador" method="get" >
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
                <p> <label>Bebida:</label>
                    <select name="bebida_codBebida" class="form">
                        <c:forEach var="bebida" items="${bebidas}">
                            <c:choose>
                                <c:when test="${bebida.codigoBebida eq bebida_codBebida}">
                          <option selected value="${bebida.codigoBebida}">${bebida.nomeBebida}</option>
                                </c:when>
                                <c:otherwise>
                          <option value="${bebida.codigoBebida}">${bebida.nomeBebida}</option>          
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </p>
                <p> <label>Quantidade:</label>
                    <input type="text" name="quantidade" value="${quantidade}" size="40" required class="form"/>
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
                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Bebida_por_vendaControlador" method="get" >

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
                            <th>Bebida</th>
                            <th>Quantidade</th>
                            <th>Preço da Venda</th>
                            <th>Alterar</th>
                            <th>Excluir</th>
                        </tr>
                    </thead>

                    <c:forEach var="bebida_por_venda" items="${bebidas_por_vendas}">
                        <tbody class="tbody">
                            <tr>
                                <td class="td">${bebida_por_venda.codigo}</td>
                                <td class="td">${bebida_por_venda.venda_codPedido.codPedido}</td>
                                <td class="td">${bebida_por_venda.bebida_codBebida.nomeBebida}</td>
                                <td class="td">${bebida_por_venda.quantidade}</td>
                                <td class="td">${bebida_por_venda.precoVenda}</td>

                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Bebida_por_vendaControlador" >
                                        <input type="hidden" name="codigo" value="${bebida_por_venda.codigo}">
                                        <input type="hidden" name="venda_codPedido" value="${bebida_por_venda.venda_codPedido.codPedido}">
                                        <input type="hidden" name="bebida_codBebida" value="${bebida_por_venda.bebida_codBebida.codigoBebida}">
                                        <input type="hidden" name="quantidade" value="${bebida_por_venda.quantidade}">
                                        <input type="hidden" name="precoVenda" value="${bebida_por_venda.precoVenda}">

                                        <input type="hidden" name="opcao" value="editar">
                                        <button class="butaoEditar" type="submit">Editar</button>
                                    </form> 
                                </td>
                                <td class="td">
                                    <form  name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/Bebida_por_vendaControlador" >
                                        <input type="hidden" name="codigo" value="${bebida_por_venda.codigo}">
                                        <input type="hidden" name="venda_codPedido" value="${bebida_por_venda.venda_codPedido.codPedido}">
                                        <input type="hidden" name="bebida_codBebida" value="${bebida_por_venda.bebida_codBebida.codigoBebida}">
                                        <input type="hidden" name="quantidade" value="${bebida_por_venda.quantidade}">
                                        <input type="hidden" name="precoVenda" value="${bebida_por_venda.precoVenda}">

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
