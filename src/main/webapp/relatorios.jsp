<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reis di Napoli - Relatórios</title>
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <style>
        /* Estilos específicos para a página de relatórios */
        
        main.container {
            max-width: 1400px; /* Página mais larga */
        }
        
        .page-title {
            text-align: center;
            margin-bottom: 40px;
            font-size: 2.5em;
            font-family: 'Cinzel Decorative', cursive; 
        }

        .report-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
            gap: 25px;
        }

        .report-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 8px 15px rgba(0,0,0,0.05);
            border-top: 4px solid var(--cor-vinho); 
            display: flex;
            flex-direction: column;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
        }
        
        .report-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
        }

        .report-card-header {
            font-family: 'Cinzel Decorative', cursive; 
            text-align: center;
            padding: 20px;
            margin: 0;
            border-bottom: 1px solid #f0f0f0;
            font-size: 1.5em;
            color: var(--cor-texto-escuro); 
        }
        
        .report-card-body {
            padding: 25px;
            display: flex;
            justify-content: center; /* Centraliza os botões */
            align-items: center;
            gap: 15px; /* Espaço entre os botões */
            flex-wrap: wrap; 
        }

        .btn-export {
            display: inline-flex;
            align-items: center;
            text-decoration: none;
            color: #fff;
            padding: 12px 20px;
            border-radius: 8px;
            font-weight: 500;
            font-family: 'Fauna One', serif; 
            font-size: 1.1em;
            transition: background-color 0.2s ease, transform 0.2s ease;
            border: none;
            cursor: pointer;
        }
        
        .btn-export:hover {
            transform: scale(1.05);
        }

        .btn-export i {
            margin-right: 10px; 
            font-size: 1.2em;
        }

        .btn-pdf {
            background-color: #d9534f; /* Vermelho PDF */
        }
        .btn-pdf:hover {
            background-color: #c9302c;
        }

        .btn-excel {
            background-color: #5cb85c; /* Verde Excel */
        }
        .btn-excel:hover {
            background-color: #449d44;
        }
        
        .grid-divider {
            grid-column: 1 / -1; 
            border: 0;
            border-top: 2px solid #eee;
            margin: 20px 0 10px 0;
        }
        
        .grid-divider-title {
            grid-column: 1 / -1;
            font-family: 'Cinzel Decorative', cursive;
            color: var(--cor-vinho);
            font-size: 1.8em;
            margin: 0 0 10px 0;
        }
        
    </style>
</head>
<body>

    <%@include file="header.jsp" %>

    <main class="container">
        <h2 class="page-title">Painel de Relatórios</h2>

        <div class="report-grid">
        
            <h3 class="grid-divider-title">Vendas e Faturamento</h3>
            <hr class="grid-divider">

            <div class="report-card">
                <div class="report-card-header">Faturamento Mensal (Completo)</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/faturamento-mensal?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/faturamento-mensal?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Vendas por Dia da Semana</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-dia-semana?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-dia-semana?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Vendas por Pagamento</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-tipo-pagamento?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-tipo-pagamento?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>
            
            <h3 class="grid-divider-title">Produtos</h3>
            <hr class="grid-divider">

            <div class="report-card">
                <div class="report-card-header">Pizzas Mais Vendidas (Completo)</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/pizzas-mais-vendidas?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/pizzas-mais-vendidas?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Bebidas Mais Vendidas (Completo)</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/bebidas-mais-vendidas?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/bebidas-mais-vendidas?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Bordas Mais Populares</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/bordas-mais-vendidas?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/bordas-mais-vendidas?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Vendas por Tamanho</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-tamanho-pizza?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-tamanho-pizza?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>
            
            <h3 class="grid-divider-title">Clientes e Equipe</h3>
            <hr class="grid-divider">
            
            <div class="report-card">
                <div class="report-card-header">Clientes por Total Gasto</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/clientes-por-gasto?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/clientes-por-gasto?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>
            
            <div class="report-card">
                <div class="report-card-header">Vendas por Bairro (Completo)</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-bairro?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/vendas-por-bairro?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

            <div class="report-card">
                <div class="report-card-header">Funcionários por Vendas</div>
                <div class="report-card-body">
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/funcionarios-por-venda?formato=pdf" class="btn-export btn-pdf"><i class="fas fa-file-pdf"></i> PDF</a>
                    <a href="${pageContext.request.contextPath}/RelatorioControlador/funcionarios-por-venda?formato=excel" class="btn-export btn-excel"><i class="fas fa-file-excel"></i> Excel</a>
                </div>
            </div>

        </div> </main>
    
    <footer class="main-footer">
        <div class="container">
            <p>&copy; 2025 Reis di Napoli. Todos os direitos reservados.</p>
        </div>
    </footer>
    
</body>
</html>