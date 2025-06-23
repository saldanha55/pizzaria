<%-- 
    Document   : Menu
    Created on : 15 de jul de 2024, 13:24:54
    Author     : 14830919612
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="interface">
            <nav>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/BebidaControlador?opcao=cancelar">Bebida</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/RecheioBordaControlador?opcao=cancelar">Recheio Borda</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/IngredienteControlador?opcao=cancelar">Ingrediente</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cliente</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">Funcionário</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar">Fornecedor</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/Bebida_por_vendaControlador?opcao=cancelar">Bebida por Venda</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/PizzaControlador?opcao=cancelar">Pizza</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/Pizza_por_vendaControlador?opcao=cancelar">Pizza por Venda</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/Ingrediente_por_pizzaControlador?opcao=cancelar">Ingrediente por Pizza</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=cancelar">Início</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/ItensVendaControlador?opcao=cancelar">Venda</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/VendaControlador?opcao=cancelar">Controle de Vendas</a></li>
                    <li><a href="${pageContext.request.contextPath}/login.jsp">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}${URL_BASE}/LogoutControlador">Logout</a></li>
                </ul>
            </nav>
        </div>
    </body>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</html>
