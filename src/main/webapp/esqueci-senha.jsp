<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reis di Napoli - Esqueci Minha Senha</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="form-wrapper">
        <div class="form-container">
            <h2 class="page-title">Recuperar Senha</h2>
            <p style="text-align: center; margin-bottom: 20px;">
                Digite seu e-mail abaixo para receber as instruções de como redefinir sua senha.
            </p>

            <% if (request.getAttribute("mensagemSucesso") != null) { %>
                <p class="status-message status-sucesso"><%= request.getAttribute("mensagemSucesso") %></p>
            <% } %>
            <% if (request.getAttribute("mensagemErro") != null) { %>
                <p class="status-message status-erro"><%= request.getAttribute("mensagemErro") %></p>
            <% } %>

            <form action="SenhaControlador" method="post">
                <input type="hidden" name="acao" value="solicitar">
                <div class="form-grid">
                    <div class="form-group">
                        <span class="icon"><i class="fas fa-envelope"></i></span>
                        <input type="email" name="email" placeholder="Digite seu e-mail" required>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enviar E-mail</button>
                </div>
            </form>
            <div class="form-link">
                <a href="login.jsp">Voltar para o Login</a>
            </div>
        </div>
    </div>
</body>
</html>