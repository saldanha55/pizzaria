<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.mycompany.pizzariaweb.modelo.entidade.*"%>
<%@page import="com.mycompany.pizzariaweb.modelo.dao.*"%>
<%@page import="com.mycompany.pizzariaweb.servico.ConverteData"%>

<%-- 
    Página para Gerenciamento de Vendas.
    Funcionalidades:
    1. Iniciar uma nova venda selecionando cliente e funcionário.
    2. Adicionar produtos (pizzas e bebidas) ao carrinho.
    3. Visualizar os itens no carrinho e o total da venda.
    4. Finalizar a venda, registrando no banco de dados.
--%>

<%
    // --- 1. INICIALIZAÇÃO DOS OBJETOS DAO ---
    // Instancia todos os DAOs necessários para as operações no banco de dados.
    ClienteDao clienteDao = new ClienteDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    PizzaDao pizzaDao = new PizzaDao();
    BebidaDao bebidaDao = new BebidaDao();
    VendaDao vendaDao = new VendaDao();
    ItensVendaDao itensVendaDao = new ItensVendaDao();

    // Busca as listas de clientes, funcionários, pizzas e bebidas para preencher os seletores (dropdowns).
    List<Cliente> clientes = clienteDao.listar();
    List<Funcionario> funcionarios = funcionarioDao.listar();
    List<Pizza> pizzas = pizzaDao.listar();
    List<Bebida> bebidas = bebidaDao.listar();

    // --- 2. GERENCIAMENTO DE ESTADO DA VENDA (SESSÃO) ---
    // A venda atual e seus itens são armazenados na sessão para persistir entre as requisições.
    Venda venda = (Venda) session.getAttribute("venda");
    List<ItensVenda> carrinho = (List<ItensVenda>) session.getAttribute("carrinho");

    // --- 3. CONTROLE DE AÇÕES ---
    // O parâmetro 'acao' no URL determina qual lógica executar.
    String acao = request.getParameter("acao");

    if (acao != null) {
        switch (acao) {
            
            // Ação para iniciar uma nova venda
            case "iniciar":
                try {
                    // Cria um novo objeto Venda com os dados do formulário.
                    venda = new Venda();
                    Cliente cliente = clienteDao.buscarPorId(Integer.parseInt(request.getParameter("cliente")));
                    Funcionario funcionario = funcionarioDao.buscarPorId(Integer.parseInt(request.getParameter("funcionario")));
                    
                    // Define os dados iniciais da venda.
                    venda.setCliente(cliente);
                    venda.setFuncionario(funcionario);
                    venda.setData(new java.util.Date()); // Data atual
                    venda.setTipoPagamento("Dinheiro"); // Valor padrão
                    venda.setDesconto(0.0);
                    venda.setComissao(0.0);

                    // Insere a venda no banco para obter um ID e a armazena na sessão.
                    venda = vendaDao.inserir(venda);
                    session.setAttribute("venda", venda);
                    
                    // Inicializa um carrinho vazio na sessão.
                    session.setAttribute("carrinho", new ArrayList<ItensVenda>());
                    
                } catch (SQLException e) {
                    // Tratamento de erro
                    out.println("Erro ao iniciar venda: " + e.getMessage());
                }
                // Redireciona para a mesma página para atualizar a interface.
                response.sendRedirect("CadastroVenda.jsp");
                return;

            // Ação para adicionar um item (pizza ou bebida) ao carrinho
            case "adicionar":
                if (venda != null) {
                    try {
                        String produtoSelecionado = request.getParameter("produto");
                        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
                        
                        // O valor do produto é no formato "tipo_id" (ex: "pizza_1" ou "bebida_3").
                        String[] parts = produtoSelecionado.split("_");
                        String tipo = parts[0];
                        int idProduto = Integer.parseInt(parts[1]);

                        ItensVenda item = new ItensVenda();
                        item.setItens_codVenda(venda);
                        item.setQuantidade(quantidade);

                        // Lógica para diferenciar pizza de bebida
                        if ("pizza".equals(tipo)) {
                            Pizza p = pizzaDao.buscarPorId(idProduto);
                            item.setItens_codPizza(p);
                            item.setValor(p.getPrecoBase()); // Usa o preço base da pizza
                        } else if ("bebida".equals(tipo)) {
                            Bebida b = bebidaDao.buscarPorId(idProduto);
                            item.setItens_codBebida(b);
                            item.setValor(b.getPreco()); // Usa o preço da bebida
                        }

                        // Insere o item no banco de dados e adiciona ao carrinho na sessão.
                        itensVendaDao.inserir(item);
                        carrinho = itensVendaDao.listarPorVenda(venda.getCodPedido());
                        session.setAttribute("carrinho", carrinho);

                    } catch (Exception e) {
                        out.println("Erro ao adicionar item: " + e.getMessage());
                    }
                }
                response.sendRedirect("CadastroVenda.jsp");
                return;

            // Ação para remover um item do carrinho
            case "remover":
                if (venda != null) {
                    try {
                        int codItemVenda = Integer.parseInt(request.getParameter("codItem"));
                        ItensVenda itemRemover = new ItensVenda();
                        itemRemover.setCodItensVenda(codItemVenda);
                        
                        // Remove o item do banco de dados.
                        itensVendaDao.excluir(itemRemover);
                        
                        // Atualiza o carrinho na sessão.
                        carrinho = itensVendaDao.listarPorVenda(venda.getCodPedido());
                        session.setAttribute("carrinho", carrinho);
                        
                    } catch (Exception e) {
                         out.println("Erro ao remover item: " + e.getMessage());
                    }
                }
                response.sendRedirect("CadastroVenda.jsp");
                return;

            // Ação para finalizar e limpar a venda
            case "finalizar":
                // Limpa os dados da sessão, encerrando a venda atual.
                session.removeAttribute("venda");
                session.removeAttribute("carrinho");
                response.sendRedirect("CadastroVenda.jsp?finalizado=true");
                return;
        }
    }
    
    // Calcula o valor total do carrinho
    double total = 0;
    if (carrinho != null) {
        for (ItensVenda item : carrinho) {
            total += item.getValor() * item.getQuantidade();
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Página de Vendas - Pizzaria</title>
    <link rel="stylesheet" type="text/css" href="css/estilo.css">
    <style>
        /* Estilos adicionais para melhorar a aparência da página de vendas */
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .container { width: 80%; margin: auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .section { border: 1px solid #ddd; padding: 15px; margin-bottom: 20px; border-radius: 5px; }
        .section h2 { margin-top: 0; border-bottom: 2px solid #f0ad4e; padding-bottom: 10px; }
        .form-group { margin-bottom: 15px; }
        .form-group label { display: block; margin-bottom: 5px; }
        .form-group select, .form-group input { width: 100%; padding: 8px; border-radius: 4px; border: 1px solid #ccc; }
        .btn { padding: 10px 15px; border: none; color: #fff; border-radius: 4px; cursor: pointer; }
        .btn-primary { background-color: #5cb85c; }
        .btn-secondary { background-color: #f0ad4e; }
        .btn-danger { background-color: #d9534f; }
        .cart-table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        .cart-table th, .cart-table td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        .cart-table th { background-color: #f2f2f2; }
        .total-row { font-weight: bold; font-size: 1.2em; }
    </style>
</head>
<body>
    <%@include file="header.jsp" %>
    <div class="container">
        <h1>Registrar Nova Venda</h1>

        <%-- Se uma venda não foi iniciada, exibe o formulário para iniciar. --%>
        <% if (venda == null) { %>
            <div class="section">
                <h2>1. Iniciar Venda</h2>
                <form action="CadastroVenda.jsp?acao=iniciar" method="POST">
                    <div class="form-group">
                        <label for="cliente">Cliente:</label>
                        <select id="cliente" name="cliente" required>
                            <% for (Cliente c : clientes) { %>
                                <option value="<%= c.getCodCliente() %>"><%= c.getNome() %></option>
                            <% } %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="funcionario">Funcionário:</label>
                        <select id="funcionario" name="funcionario" required>
                            <% for (Funcionario f : funcionarios) { %>
                                <option value="<%= f.getCodFuncionario() %>"><%= f.getNome() %></option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Iniciar Venda</button>
                </form>
            </div>
        <% } else { %>
            <%-- Se a venda já foi iniciada, exibe os detalhes e as opções de adicionar itens. --%>
            <div class="section">
                <h2>Venda em Andamento (Pedido #<%= venda.getCodPedido() %>)</h2>
                <p><strong>Cliente:</strong> <%= venda.getCliente().getNome() %></p>
                <p><strong>Atendente:</strong> <%= venda.getFuncionario().getNome() %></p>
            </div>

            <div class="section">
                <h2>2. Adicionar Itens ao Pedido</h2>
                <form action="CadastroVenda.jsp?acao=adicionar" method="POST">
                    <div class="form-group">
                        <label for="produto">Produto:</label>
                        <select id="produto" name="produto" required>
                            <optgroup label="Pizzas">
                                <% for (Pizza p : pizzas) { %>
                                    <%-- O valor combina tipo e ID para fácil processamento --%>
                                    <option value="pizza_<%= p.getCodPizza() %>">
                                        <%= p.getNome() %> - R$ <%= String.format("%.2f", p.getPrecoBase()) %>
                                    </option>
                                <% } %>
                            </optgroup>
                            <optgroup label="Bebidas">
                                <% for (Bebida b : bebidas) { %>
                                    <option value="bebida_<%= b.getCodBebida() %>">
                                        <%= b.getNome() %> - R$ <%= String.format("%.2f", b.getPreco()) %>
                                    </option>
                                <% } %>
                            </optgroup>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="quantidade">Quantidade:</label>
                        <input type="number" id="quantidade" name="quantidade" value="1" min="1" required>
                    </div>
                    <button type="submit" class="btn btn-secondary">Adicionar ao Carrinho</button>
                </form>
            </div>

            <div class="section">
                <h2>3. Carrinho de Compras</h2>
                <table class="cart-table">
                    <thead>
                        <tr>
                            <th>Produto</th>
                            <th>Quantidade</th>
                            <th>Preço Unit.</th>
                            <th>Subtotal</th>
                            <th>Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (carrinho == null || carrinho.isEmpty()) { %>
                            <tr>
                                <td colspan="5" style="text-align:center;">O carrinho está vazio.</td>
                            </tr>
                        <% } else { %>
                            <% for (ItensVenda item : carrinho) { %>
                                <tr>
                                    <td>
                                        <%-- Exibe o nome da pizza ou da bebida --%>
                                        <%= (item.getItens_codPizza() != null) ? item.getItens_codPizza().getNome() : item.getItens_codBebida().getNome() %>
                                    </td>
                                    <td><%= item.getQuantidade() %></td>
                                    <td>R$ <%= String.format("%.2f", item.getValor()) %></td>
                                    <td>R$ <%= String.format("%.2f", item.getValor() * item.getQuantidade()) %></td>
                                    <td>
                                        <a href="CadastroVenda.jsp?acao=remover&codItem=<%= item.getCodItensVenda() %>" class="btn-danger" style="padding: 5px 8px; text-decoration: none; color: white; border-radius: 3px;">Remover</a>
                                    </td>
                                </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                    <tfoot>
                        <tr class="total-row">
                            <td colspan="3" style="text-align:right;">Total:</td>
                            <td colspan="2">R$ <%= String.format("%.2f", total) %></td>
                        </tr>
                    </tfoot>
                </table>
            </div>

            <div class="section">
                <h2>4. Finalizar Venda</h2>
                <form action="CadastroVenda.jsp?acao=finalizar" method="POST">
                    <%-- Aqui você pode adicionar campos para desconto, tipo de pagamento, etc. --%>
                    <%-- A lógica para atualizar a venda com esses dados seria adicionada no case "finalizar". --%>
                    <button type="submit" class="btn btn-primary">Finalizar e Iniciar Nova Venda</button>
                </form>
            </div>

        <% } %>
        
        <% if ("true".equals(request.getParameter("finalizado"))) { %>
            <p style="color: green; font-weight: bold; text-align: center;">Venda finalizada com sucesso!</p>
        <% } %>
        
        <br/>
        <a href="index.jsp">Voltar ao Menu</a>

    </div>
</body>
</html>
