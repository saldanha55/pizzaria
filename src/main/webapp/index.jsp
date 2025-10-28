<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
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
            <%@include file="header.jsp" %>

    <main class="container">
        <section class="welcome-section">
            <h2>Bem-vindo ao Sistema de Gestão</h2>
            <p>Escolha uma das opções abaixo para começar.</p>
        </section>

        <nav class="main-nav">
            <a href="CadastroVenda.jsp" class="nav-card">
                <div class="card-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-basket-fill" viewBox="0 0 16 16">
                        <path d="M5.071 1.243a.5.5 0 0 1 .858.514L3.383 6h9.234L10.07 1.757a.5.5 0 1 1 .858-.514L13.783 6H15.5a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-.623l-1.844 6.456a.75.75 0 0 1-.722.544H3.69a.75.75 0 0 1-.722-.544L1.123 8H.5a.5.5 0 0 1-.5-.5v-1A.5.5 0 0 1 .5 6h1.717L5.07 1.243zM3.5 10.5a.5.5 0 1 0-1 0v1.5a.5.5 0 1 0 1 0v-1.5zm2.5 0a.5.5 0 1 0-1 0v1.5a.5.5 0 1 0 1 0v-1.5zm2.5 0a.5.5 0 1 0-1 0v1.5a.5.5 0 1 0 1 0v-1.5zm2.5 0a.5.5 0 1 0-1 0v1.5a.5.5 0 1 0 1 0v-1.5zm2.5 0a.5.5 0 1 0-1 0v1.5a.5.5 0 1 0 1 0v-1.5z"/>
                    </svg>
                </div>
                <h3>Nova Venda</h3>
                <p>Iniciar um novo pedido para um cliente.</p>
            </a>
            <a href="Menu.jsp" class="nav-card">
                 <div class="card-icon">
                    <svg xmlns="http://www.w3.org/2000/svg" width="48" height="48" fill="currentColor" class="bi bi-gear-fill" viewBox="0 0 16 16">
                        <path d="M9.405 1.05c-.413-1.4-2.397-1.4-2.81 0l-.1.34a1.464 1.464 0 0 1-2.105.872l-.31-.17c-1.283-.698-2.686.705-1.987 1.987l.169.311a1.464 1.464 0 0 1-.872 2.105l-.34.1c-1.4.413-1.4 2.397 0 2.81l.34.1a1.464 1.464 0 0 1 .872 2.105l-.17.31c-.698 1.283.705 2.686 1.987 1.987l.311-.169a1.464 1.464 0 0 1 2.105.872l.1.34c.413 1.4 2.397 1.4 2.81 0l.1-.34a1.464 1.464 0 0 1 2.105-.872l.31.17c1.283.698 2.686-.705-1.987-1.987l-.169-.311a1.464 1.464 0 0 1 .872-2.105l.34-.1c1.4-.413-1.4-2.397 0-2.81l.34-.1a1.464 1.464 0 0 1 .872-2.105l.17-.31c.698-1.283-.705-2.686-1.987-1.987l-.311.169a1.464 1.464 0 0 1-2.105-.872l-.1-.34zM8 10.93a2.929 2.929 0 1 1 0-5.858 2.929 2.929 0 0 1 0 5.858z"/>
                    </svg>
                </div>
                <h3>Gerenciar Cadastros</h3>
                <p>Administrar clientes, produtos e funcionários.</p>
            </a>
        </nav>
    </main>

    <footer class="main-footer">
        <div class="container">
            <p>&copy; 2025 Reis di Napoli. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>
</html>
