package com.mycompany.pizzariaweb.controlador;

// Todos os DAOs necessários para os relatórios
import com.mycompany.pizzariaweb.modelo.dao.BebidaDao;
import com.mycompany.pizzariaweb.modelo.dao.ClienteDao;
import com.mycompany.pizzariaweb.modelo.dao.FuncionarioDao;
import com.mycompany.pizzariaweb.modelo.dao.PizzaDao;
import com.mycompany.pizzariaweb.modelo.dao.VendaDao;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import com.mycompany.pizzariaweb.servico.ExcelExportService;
import com.mycompany.pizzariaweb.servico.PdfExportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Este é o ÚNICO servlet de relatórios que você precisa.
public class RelatorioControlador extends HttpServlet {

    // --- Serviços de Exportação ---
    private final ExcelExportService excelService = new ExcelExportService();
    // O PdfExportService será instanciado por requisição

    // --- DAOs para buscar os dados ---
    private final ClienteDao clienteDao = new ClienteDao();
    private final PizzaDao pizzaDao = new PizzaDao();
    private final BebidaDao bebidaDao = new BebidaDao();
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();
    private final VendaDao vendaDao = new VendaDao();
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String formato = request.getParameter("formato");
        if (formato == null || formato.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O parâmetro 'formato' (excel ou pdf) é obrigatório.");
            return;
        }

        // A URL (ex: /RelatorioControlador/pizzas-mais-vendidas) é lida aqui
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O tipo de relatório não foi especificado na URL (ex: /RelatorioControlador/clientes-por-bairro).");
            return;
        }

        RelatorioDTO dto = null;
        String nomeArquivo = "relatorio";
        String tituloRelatorio = "Relatório";
        String[] titulosColunas = {"Label", "Valor"}; 

        try {
            // Roteamento para buscar os dados de cada relatório
            switch (pathInfo) {
                // Relatórios do ClienteDao
                case "/clientes-por-bairro":
                    dto = clienteDao.getRelatorioClientesPorBairro(); 
                    nomeArquivo = "clientes_por_bairro";
                    tituloRelatorio = "Relatório de Clientes por Bairro";
                    titulosColunas = new String[]{"Bairro", "Total de Clientes"};
                    break;
                case "/clientes-por-gasto": // MUDANÇA: URL alterada de /top-5-clientes
                    dto = clienteDao.getTop5Clientes(); // O método ainda se chama getTop5Clientes
                    nomeArquivo = "clientes_por_gasto";
                    tituloRelatorio = "Relatório de Clientes por Total Gasto"; // MUDANÇA: Título alterado
                    titulosColunas = new String[]{"Cliente", "Total Gasto (R$)"};
                    break;
                    
                // Relatórios do PizzaDao
                case "/pizzas-mais-vendidas":
                    dto = pizzaDao.getPizzasMaisVendidas();
                    nomeArquivo = "pizzas_mais_vendidas_completo"; // MUDANÇA: Nome do arquivo
                    tituloRelatorio = "Relatório de Pizzas Mais Vendidas (Completo)"; // MUDANÇA: Título
                    titulosColunas = new String[]{"Pizza", "Total Vendido"};
                    break;
                case "/bordas-mais-vendidas":
                    dto = pizzaDao.getBordasMaisVendidas();
                    nomeArquivo = "bordas_mais_vendidas";
                    tituloRelatorio = "Sabores de Borda Mais Populares";
                    titulosColunas = new String[]{"Sabor da Borda", "Total"};
                    break;
                case "/vendas-por-tamanho-pizza":
                    dto = pizzaDao.getVendasPorTamanhoPizza();
                    nomeArquivo = "vendas_por_tamanho_pizza";
                    tituloRelatorio = "Vendas por Tamanho de Pizza";
                    titulosColunas = new String[]{"Tamanho", "Total Vendido"};
                    break;
                    
                // Relatórios do BebidaDao
                case "/bebidas-mais-vendidas":
                    dto = bebidaDao.getBebidasMaisVendidas();
                    nomeArquivo = "bebidas_mais_vendidas_completo"; // MUDANÇA: Nome do arquivo
                    tituloRelatorio = "Relatório de Bebidas Mais Vendidas (Completo)"; // MUDANÇA: Título
                    titulosColunas = new String[]{"Bebida", "Total Vendido"};
                    break;

                // Relatórios do FuncionarioDao
                case "/funcionarios-por-venda": // MUDANÇA: URL alterada de /top-funcionarios
                    dto = funcionarioDao.getTopFuncionariosPorVenda();
                    nomeArquivo = "funcionarios_por_venda";
                    tituloRelatorio = "Relatório de Funcionários por Nº de Vendas"; // MUDANÇA: Título
                    titulosColunas = new String[]{"Funcionário", "Nº de Vendas"};
                    break;

                // Relatórios do VendaDao
                case "/vendas-dia-semana":
                    dto = vendaDao.getVendasPorDiaDaSemana();
                    nomeArquivo = "vendas_dia_semana";
                    tituloRelatorio = "Vendas por Dia da Semana";
                    titulosColunas = new String[]{"Dia da Semana", "Nº de Vendas"};
                    break;
                case "/faturamento-mensal":
                dto = vendaDao.getFaturamentoMensal();
                nomeArquivo = "faturamento_mensal_completo";
                tituloRelatorio = "Relatório de Faturamento Mensal (Completo)";
                // MUDANÇA: Título da coluna atualizado
                titulosColunas = new String[]{"Mês/Ano", "Faturamento (R$)"}; 
                break;
                case "/vendas-por-tipo-pagamento":
                    dto = vendaDao.getVendasPorTipoPagamento();
                    nomeArquivo = "vendas_por_tipo_pagamento";
                    tituloRelatorio = "Vendas por Tipo de Pagamento";
                    titulosColunas = new String[]{"Tipo de Pagamento", "Total"};
                    break;
                case "/vendas-por-bairro":
                    dto = vendaDao.getVendasPorBairro();
                    nomeArquivo = "vendas_por_bairro_completo"; // MUDANÇA
                    tituloRelatorio = "Relatório de Vendas por Bairro (Completo)"; // MUDANÇA
                    titulosColunas = new String[]{"Bairro", "Total de Vendas"};
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Relatório não encontrado para o caminho: " + pathInfo);
                    return;
            }

            // --- Lógica de Exportação ---
            
            if ("excel".equalsIgnoreCase(formato)) {
                excelService.gerarPlanilha(response, dto, nomeArquivo + ".xlsx", tituloRelatorio, titulosColunas);
            
            } else if ("pdf".equalsIgnoreCase(formato)) {
                PdfExportService pdfService = new PdfExportService(dto);
                pdfService.gerarPdf(response, nomeArquivo, tituloRelatorio, titulosColunas);
            
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de exportação inválido. Use 'excel' ou 'pdf'.");
            }

        } catch (Exception e) {
            System.err.println("Erro grave ao gerar relatório " + pathInfo + " para " + formato);
            e.printStackTrace();
            throw new ServletException("Erro interno ao gerar o relatório: Ocorreu um erro ao gerar seu relatório." + e.getMessage(), e);
        }
    }
}