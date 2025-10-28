<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.pizzariaweb.modelo.entidade.Funcionario"%>
<%@page import="com.mycompany.pizzariaweb.modelo.entidade.Usuario" %>
<%-- CÓDIGO CORRETO --%>
<%
    
    Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
    String nomeUsuario = ""; // Valor padrão
    if (usuario != null && usuario.getFuncionario() != null) {
        nomeUsuario = usuario.getFuncionario().getNome();
    }
%>
<header class="main-header">
    <div class="container">
        <div class="logo">
            <h1>Reis di Napoli</h1>
            <p class="slogan">Cada fatia é realeza.</p>
        </div>
        <div class="user-info">
            <span>Olá, <%= nomeUsuario %>!</span>
            <!-- CORREÇÃO: O link agora aponta para o LoginControlador para acionar o método doGet (logout) -->
            <a href="LoginControlador" class="logout-button">Sair</a>
        </div>
    </div>
</header>

<nav class="management-navbar">
    <div class="container">
        <a href="index.jsp">Início</a>
        <a href="VendaControlador">Vendas</a>
        <a href="ClienteControlador">Clientes</a>
        <a href="FuncionarioControlador">Funcionários</a>
        <a href="UsuarioControlador">Usuários</a>
        <a href="PizzaControlador">Pizzas</a>
        <a href="BebidaControlador">Bebidas</a>
        <a href="IngredienteControlador">Ingredientes</a>
        <a href="RecheioBordaControlador">Bordas</a>
        <a href="Ingrediente_por_pizzaControlador">Receitas</a>
        <a href="FornecedorControlador">Fornecedores</a>
        <a href="relatorios.jsp">Relatórios</a>  <%-- ADICIONADO AQUI --%>
    </div>
</nav>
