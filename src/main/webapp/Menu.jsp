<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reis di Napoli - Gerenciamento</title>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Cinzel:wght@700&family=Lora:ital,wght@0,400;0,500;1,400&display=swap" rel="stylesheet">
</head>
<body>
    <header class="main-header">
        <div class="container">
            <div class="logo">
                <h1>Reis di Napoli</h1>
                <p class="slogan">Cada fatia é realeza.</p>
            </div>
            <a href="index.jsp" class="back-link">Voltar ao Início</a>
        </div>
    </header>

    <main class="container">
        <h2 class="page-title">Painel de Gerenciamento</h2>
        <nav class="management-nav">
            <a href="CadastroCliente.jsp" class="nav-card">
                <h3>Clientes</h3>
                <p>Adicionar, editar e remover clientes.</p>
            </a>
            <a href="CadastroFuncionario.jsp" class="nav-card">
                <h3>Funcionários</h3>
                <p>Gerenciar a equipe da pizzaria.</p>
            </a>
            <a href="CadastroPizza.jsp" class="nav-card">
                <h3>Pizzas</h3>
                <p>Definir sabores e preços das pizzas.</p>
            </a>
            <a href="CadastroBebida.jsp" class="nav-card">
                <h3>Bebidas</h3>
                <p>Gerenciar o estoque de bebidas.</p>
            </a>
            <a href="CadastroIngrediente.jsp" class="nav-card">
                <h3>Ingredientes</h3>
                <p>Controlar o estoque de ingredientes.</p>
            </a>
             <a href="CadastroRecheioBorda.jsp" class="nav-card">
                <h3>Bordas</h3>
                <p>Gerenciar tipos e preços das bordas.</p>
            </a>
            <a href="CadastroFornecedor.jsp" class="nav-card">
                <h3>Fornecedores</h3>
                <p>Administrar os fornecedores.</p>
            </a>
             <a href="CadastroIngrediente_por_pizza.jsp" class="nav-card">
                <h3>Receitas</h3>
                <p>Montar a receita de cada pizza.</p>
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
