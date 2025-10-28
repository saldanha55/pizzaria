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

        <h1 class="tit">Cadastro Funcionario</h1>
        <div class="cadastro">
            
            <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                <p> <label>Nome:</label>
                    <input type="text" name="nomeFuncionario" value="${nomeFuncionario}" size="40" required class="form"/>
                </p>
                <p> <label>Cpf:</label>
                    <input type="text" name="cpfFuncionario" value="${cpfFuncionario}" size="40" class="form"/>
                </p>
                <p> <label>Salário:</label>
                    <input type="text" name="salarioFuncionario" value="${salarioFuncionario}" size="40" class="form"/>
                </p>
                <p> <label>Cargo:</label>
                    <input type="text" name="cargoFuncionario" value="${cargoFuncionario}" size="40" class="form"/>
                </p>
                <p> <label>Bairro:</label>
                    <input type="text" name="bairroFuncionario" value="${bairroFuncionario}" size="40" required class="form"/>
                </p>
                <p> <label>Rua:</label>
                    <input type="text" name="ruaFuncionario" value="${ruaFuncionario}" size="40" required class="form"/>
                </p>
                <p> <label>Número:</label>
                    <input type="text" name="numeroFuncionario" value="${numeroFuncionario}" size="40" required class="form"/>
                </p>
                <p> <label>Telefone:</label>
                    <input type="text" name="telefoneFuncionario" value="${telefoneFuncionario}" size="40" required class="form"/>
                </p>
                <p> <label>Email:</label>
                    <input type="text" name="emailFuncionario" value="${emailFuncionario}" size="40" class="form"/>
                </p>
                <input type="hidden" name="opcao" value="${opcao}">
                <input type="hidden" name="codigoFuncionario" value="${codigoFuncionario}">
                
                    <table border="0">
                        <tr>
                            <td>
                                <input class="butaoSalvar" type="submit" name="Salvar" value="Salvar" />
                            </td>
                </form>

                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
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
                        <th>Cpf</th>
                        <th>Salário</th>
                        <th>Cargo</th>
                        <th>Bairro</th>
                        <th>Rua</th>
                        <th>Número</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <c:forEach var="funcionario" items="${funcionarios}">
                    <tbody class="tbody">
                        <tr>
                            <td class="td">${funcionario.codigoFuncionario}</td>
                            <td class="td">${funcionario.nomeFuncionario}</td>
                            <td class="td">${funcionario.cpfFuncionario}</td>
                            <td class="td">${funcionario.salarioFuncionario}</td>
                            <td class="td">${funcionario.cargoFuncionario}</td>
                            <td class="td">${funcionario.bairroFuncionario}</td>
                            <td class="td">${funcionario.ruaFuncionario}</td>
                            <td class="td">${funcionario.numeroFuncionario}</td>
                            <td class="td">${funcionario.telefoneFuncionario}</td>
                            <td class="td">${funcionario.emailFuncionario}</td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                    <input type="hidden" name="codigoFuncionario" value="${funcionario.codigoFuncionario}">
                                    <input type="hidden" name="nomeFuncionario" value="${funcionario.nomeFuncionario}">
                                    <input type="hidden" name="cpfFuncionario" value="${funcionario.cpfFuncionario}">
                                    <input type="hidden" name="salarioFuncionario" value="${funcionario.salarioFuncionario}">
                                    <input type="hidden" name="cargoFuncionario" value="${funcionario.cargoFuncionario}">
                                    <input type="hidden" name="bairroFuncionario" value="${funcionario.bairroFuncionario}">
                                    <input type="hidden" name="ruaFuncionario" value="${funcionario.ruaFuncionario}">
                                    <input type="hidden" name="numeroFuncionario" value="${funcionario.numeroFuncionario}">
                                    <input type="hidden" name="telefoneFuncionario" value="${funcionario.telefoneFuncionario}">
                                    <input type="hidden" name="emailFuncionario" value="${funcionario.emailFuncionario}">
                                    <input type="hidden" name="opcao" value="editar">
                                    <button class="butaoEditar" type="submit">Editar</button>
                                </form>
                            </td>
                            <td class="td">
                                <form name="cadastro" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador">
                                    <input type="hidden" name="codigoFuncionario" value="${funcionario.codigoFuncionario}">
                                    <input type="hidden" name="nomeFuncionario" value="${funcionario.nomeFuncionario}">
                                    <input type="hidden" name="cpfFuncionario" value="${funcionario.cpfFuncionario}">
                                    <input type="hidden" name="salarioFuncionario" value="${funcionario.salarioFuncionario}">
                                    <input type="hidden" name="cargoFuncionario" value="${funcionario.cargoFuncionario}">
                                    <input type="hidden" name="bairroFuncionario" value="${funcionario.bairroFuncionario}">
                                    <input type="hidden" name="ruaFuncionario" value="${funcionario.ruaFuncionario}">
                                    <input type="hidden" name="numeroFuncionario" value="${funcionario.numeroFuncionario}">
                                    <input type="hidden" name="telefoneFuncionario" value="${funcionario.telefoneFuncionario}">
                                    <input type="hidden" name="emailFuncionario" value="${funcionario.emailFuncionario}">
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
