<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reis di Napoli - Redefinir Senha</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="form-wrapper">
        <div class="form-container">
            <h2 class="page-title">Crie sua Nova Senha</h2>

            <% if (request.getAttribute("mensagemErro") != null) { %>
                <p class="status-message status-erro"><%= request.getAttribute("mensagemErro") %></p>
            <% } %>

            <form action="SenhaControlador" method="post">
                <input type="hidden" name="acao" value="redefinir">
                <input type="hidden" name="token" value="<%= request.getParameter("token") %>">
                
                <div class="form-grid">
                    <div class="form-group">
                        <span class="icon"><i class="fas fa-lock"></i></span>
                        <input type="password" name="novaSenha" placeholder="Digite a nova senha" required>
                    </div>
                    <div class="form-group">
                        <span class="icon"><i class="fas fa-lock"></i></span>
                        <input type="password" name="confirmarSenha" placeholder="Confirme a nova senha" required>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Salvar Nova Senha</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>