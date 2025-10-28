<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reis di Napoli - Início</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Cinzel+Decorative:wght@700&family=Fauna+One&display=swap" rel="stylesheet">

</head>
<body>
    <div class="form-wrapper">
        <div class="form-container">
            <div class="logo">
                <h1>Reis di Napoli</h1>
                <p class="slogan">Cada fatia é realeza.</p>
            </div>
            <h2 class="page-title">Acessar o Sistema</h2>

            <% if (request.getAttribute("mensagemSucesso") != null) { %>
                <p class="status-message status-sucesso"><%= request.getAttribute("mensagemSucesso") %></p>
            <% } %>
            <% if (request.getAttribute("mensagemErro") != null) { %>
                <p class="status-message status-erro"><%= request.getAttribute("mensagemErro") %></p>
            <% } %>
            
            <form action="LoginControlador" method="post">
                <input type="hidden" name="acao" value="login">
                <div class="form-grid">
                    <div class="form-group">
                        <span class="icon"><i class="fas fa-user"></i></span>
                        <input type="text" name="login" placeholder="Login" required>
                    </div>
                    <div class="form-group">
                        <span class="icon"><i class="fas fa-lock"></i></span>
                        <input type="password" name="senha" placeholder="Senha" required>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Entrar</button>
                </div>
            </form>

            <div class="form-link">
                <a href="esqueci-senha.jsp">Esqueci minha senha</a>
            </div>
            
            <div class="form-link">
                Não tem uma conta? <a href="LoginControlador?acao=registrar">Cadastre-se</a>
            </div>
        </div>
    </div>
</body>
</html>