<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.pizzariaweb.modelo.entidade.Funcionario"%>
<%@page import="com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao"%>
<%
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    List<Funcionario> funcionarios = funcionarioDao.listar();
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reis di Napoli - Cadastro de Usuário</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="css/cadastro-style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Cinzel+Decorative:wght@700&family=Fauna+One&display=swap" rel="stylesheet">
</head>
<body>
    <header class="main-header">
        <div class="container">
            <div class="logo">
                <h1>Reis di Napoli</h1>
                <p class="slogan">Cada fatia é realeza.</p>
            </div>
        </div>
    </header>

    <main class="container form-wrapper">
        <div class="form-container">
            <h2 class="page-title" style="font-size: 2em;">Crie sua Conta</h2>
            <%-- CÓDIGO PARA EXIBIR AS MENSAGENS --%>
<% if (request.getAttribute("mensagemSucesso") != null) { %>
    <div class="alert alert-success" style="color: green;">
        <%= request.getAttribute("mensagemSucesso") %>
    </div>
<% } %>
<% if (request.getAttribute("mensagemErro") != null) { %>
    <div class="alert alert-danger" style="color: red;">
        <%= request.getAttribute("mensagemErro") %>
    </div>
<% } %>
            <form action="UsuarioControlador" method="POST">
                <input type="hidden" name="acao" value="salvar">

                <div class="form-grid">
                    <div class="form-group">
                        <input type="text" id="login" name="login" required placeholder="Crie um login">
                        <span class="icon">
                           <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" viewBox="0 0 16 16"><path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/></svg>
                        </span>
                    </div>
                    <div class="form-group">
                        <input type="password" id="senha" name="senha" required placeholder="Crie uma senha">
                        <span class="icon">
                           <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" viewBox="0 0 16 16"><path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/></svg>
                        </span>
                    </div>
                    <div class="form-group">
                        <input type="email" id="email" name="email" required placeholder="Seu email">
                        <span class="icon">
                           <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" viewBox="0 0 16 16"><path d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555zM0 4.697v7.104l5.803-3.558L0 4.697zM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757zm3.436-.586L16 11.803V4.697l-5.803 3.546z"/></svg>
                        </span>
                    </div>
                    <div class="form-group">
                        <select id="codFuncionario" name="codFuncionario" required>
                            <option value="" disabled selected>Selecione seu nome</option>
                            <% for (Funcionario funcionario : funcionarios) { %>
                                <option value="<%= funcionario.getCodFuncionario() %>">
                                    <%= funcionario.getNome() %>
                                </option>
                            <% } %>
                        </select>
                         <span class="icon">
                           <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" viewBox="0 0 16 16"><path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2zm4.5 0a.5.5 0 0 0 0 1h3a.5.5 0 0 0 0-1h-3zM8 11a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm5 2.755C12.146 12.825 10.623 12 8 12s-4.146.826-5 1.755V14a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1v-.245z"/></svg>
                        </span>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary" >Cadastrar</button>
                </div>
            </form>
             <div class="form-link">
                <p>Já tem uma conta? <a href="login.jsp">Faça o login</a></p>
            </div>
        </div>
    </main>

    <footer class="main-footer">
        <div class="container">
            <p>&copy; 2024 Reis di Napoli. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>
</html>
