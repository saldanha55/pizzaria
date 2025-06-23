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
        <h1 class="tit">Cadastro Fornecedor</h1>
        <div class="cadastro">
            
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                <p> <label>Nome:</label>
                    <input type="text" name="nomeFornecedor" value="${nomeFornecedor}" size="40" required class="form"/>
                </p>
                <p> <label>Telefone:</label>
                    <input type="text" name="telefoneFornecedor" value="${telefoneFornecedor}" size="40" required class="form"/>
                </p>
                <p> <label>Email:</label>
                    <input type="text" name="emailFornecedor" value="${emailFornecedor}" size="40"  class="form"/>
                </p>
                <p> <label>CPF/CPNJ:</label>
                    <input type="text" name="cpfFornecedor" value="${cpfFornecedor}" size="40"  class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoFornecedor" value="${codigoFornecedor}">
                
                    <table border="0">
                        <tr>
                            <td>
                                <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                            </td>
                </form>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
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
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Cpf</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="fornecedor" items="${fornecedores}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${fornecedor.codigoFornecedor}</td>
                            <td class="td">${fornecedor.nomeFornecedor}</td>
                            <td class="td">${fornecedor.telefoneFornecedor}</td>
                            <td class="td">${fornecedor.emailFornecedor}</td>
                            <td class="td">${fornecedor.cpfFornecedor}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                                    <input type="hidden" name="codigoFornecedor" value="${fornecedor.codigoFornecedor}">
                                    <input type="hidden" name="nomeFornecedor" value="${fornecedor.nomeFornecedor}">
                                    <input type="hidden" name="telefoneFornecedor" value="${fornecedor.telefoneFornecedor}">
                                    <input type="hidden" name="emailFornecedor" value="${fornecedor.emailFornecedor}">
                                    <input type="hidden" name="cpfFornecedor" value="${fornecedor.cpfFornecedor}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador">
                                    <input type="hidden" name="codigoFornecedor" value="${fornecedor.codigoFornecedor}">
                                    <input type="hidden" name="nomeFornecedor" value="${fornecedor.nomeFornecedor}">
                                    <input type="hidden" name="telefoneFornecedor" value="${fornecedor.telefoneFornecedor}">
                                    <input type="hidden" name="emailFornecedor" value="${fornecedor.emailFornecedor}">
                                    <input type="hidden" name="cpfFornecedor" value="${fornecedor.cpfFornecedor}">
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
