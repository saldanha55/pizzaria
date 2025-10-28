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

        <h1 class="tit">Cadastro Cliente</h1>
        <div class="cadastro">

            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                <p> <label>Nome:</label>
                    <input type="text" name="nomeCliente" value="${nomeCliente}" size="40" required class="form"/>
                </p>
                <p> <label>Telefone:</label>
                    <input type="text" name="telefoneCliente" value="${telefoneCliente}" size="40" required class="form"/>
                </p>
                <p> <label>Cpf:</label>
                    <input type="text" name="cpfCliente" value="${cpfCliente}" size="40" class="form"/>
                </p>
                <p> <label>Bairro:</label>
                    <input type="text" name="bairroCliente" value="${bairroCliente}" size="40" required class="form"/>
                </p>
                <p> <label>Rua:</label>
                    <input type="text" name="ruaCliente" value="${ruaCliente}" size="40" required class="form"/>
                </p>
                <p> <label>Número:</label>
                    <input type="text" name="numeroCliente" value="${numeroCliente}" size="40" required class="form"/>
                </p>
                <p> <label>Email:</label>
                    <input type="text" name="emailCliente" value="${emailCliente}" size="40" class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoCliente" value="${codigoCliente}">

                <table border="0">
                    <tr>
                        <td>
                            <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                        </td>
                        </form>

                    <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
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
                        <th>Cpf</th>
                        <th>Bairro</th>
                        <th>Rua</th>
                        <th>Número</th>
                        <th>Email</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="cliente" items="${clientes}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${cliente.codigoCliente}</td>
                            <td class="td">${cliente.nomeCliente}</td>
                            <td class="td">${cliente.telefoneCliente}</td>
                            <td class="td">${cliente.cpfCliente}</td>
                            <td class="td">${cliente.bairroCliente}</td>
                            <td class="td">${cliente.ruaCliente}</td>
                            <td class="td">${cliente.numeroCliente}</td>
                            <td class="td">${cliente.emailCliente}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                    <input type="hidden" name="codigoCliente" value="${cliente.codigoCliente}">
                                    <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}">
                                    <input type="hidden" name="telefoneCliente" value="${cliente.telefoneCliente}">
                                    <input type="hidden" name="cpfCliente" value="${cliente.cpfCliente}">
                                    <input type="hidden" name="bairroCliente" value="${cliente.bairroCliente}">
                                    <input type="hidden" name="ruaCliente" value="${cliente.ruaCliente}">
                                    <input type="hidden" name="numeroCliente" value="${cliente.numeroCliente}">
                                    <input type="hidden" name="emailCliente" value="${cliente.emailCliente}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador">
                                    <input type="hidden" name="codigoCliente" value="${cliente.codigoCliente}">
                                    <input type="hidden" name="nomeCliente" value="${cliente.nomeCliente}">
                                    <input type="hidden" name="telefoneCliente" value="${cliente.telefoneCliente}">
                                    <input type="hidden" name="cpfCliente" value="${cliente.cpfCliente}">
                                    <input type="hidden" name="bairroCliente" value="${cliente.bairroCliente}">
                                    <input type="hidden" name="ruaCliente" value="${cliente.ruaCliente}">
                                    <input type="hidden" name="numeroCliente" value="${cliente.numeroCliente}">
                                    <input type="hidden" name="emailCliente" value="${cliente.emailCliente}">
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
