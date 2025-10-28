package com.mycompany.pizzariaweb.servico;

// Imports da biblioteca (com.lowagie.text.*)
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
// Import do DTO deste projeto
import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import jakarta.servlet.http.HttpServletResponse;
// Import para a cor
import java.awt.Color;
import java.io.IOException;

public class PdfExportService {

    private RelatorioDTO dto;

    public PdfExportService(RelatorioDTO dto) {
        this.dto = dto;
    }

    /**
     * Cria e estiliza o cabeçalho da tabela no PDF.
     * @param table A tabela onde o cabeçalho será adicionado.
     * @param titulosColunas Os nomes das colunas.
     */
    private void criarCabecalhoTabela(PdfPTable table, String[] titulosColunas) {
        Font fontHeader = new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE);
        
        for (String headerTitle : titulosColunas) {
            PdfPCell cell = new PdfPCell();
            // Usando uma cor vinho para combinar com seu CSS
            cell.setBackgroundColor(new Color(120, 0, 0)); // Cor --cor-vinho
            cell.setPadding(5);
            cell.setPhrase(new Phrase(headerTitle, fontHeader));
            cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    /**
     * Preenche a tabela com os dados do RelatorioDTO.
     * @param table A tabela onde os dados serão adicionados.
     */
    private void escreverDadosTabela(PdfPTable table) {
        Font fontData = new Font(Font.HELVETICA, 10, Font.NORMAL);
        
        for (int i = 0; i < dto.getLabels().size(); i++) {
            // Célula 0: Label
            PdfPCell cellLabel = new PdfPCell(new Phrase(dto.getLabels().get(i), fontData));
            cellLabel.setPadding(4);
            table.addCell(cellLabel);

            // Célula 1: Valor
            Object value = dto.getValues().get(i);
            String valorStr = (value != null) ? value.toString() : "";
            PdfPCell cellValue = new PdfPCell(new Phrase(valorStr, fontData));
            cellValue.setPadding(4);
            cellValue.setHorizontalAlignment(Paragraph.ALIGN_LEFT);
            table.addCell(cellValue);
        }
    }

    /**
     * Método público para gerar e enviar o arquivo PDF na resposta HTTP.
     * @param response A resposta do servlet
     * @param nomeArquivo O nome do arquivo (ex: "relatorio_clientes")
     * @param tituloRelatorio O título que aparecerá no topo do PDF
     * @param titulosColunas Os títulos das colunas da tabela
     * @throws IOException
     * @throws DocumentException
     */
    public void gerarPdf(HttpServletResponse response, String nomeArquivo, String tituloRelatorio, String[] titulosColunas) throws IOException, DocumentException {
        
        response.setContentType("application/pdf");
        // Adiciona a extensão .pdf ao nome do arquivo
        response.setHeader("Content-Disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\"");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // 1. Título do Documento
        Font fontTitulo = new Font(Font.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph(tituloRelatorio, fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);
        
        // Adiciona um espaço
        document.add(new Paragraph("\n\n"));

        // 2. Tabela de Dados
        PdfPTable table = new PdfPTable(titulosColunas.length);
        table.setWidthPercentage(100);
        
        // Ajusta o tamanho das colunas (Ex: Coluna 1 com 70% e Coluna 2 com 30%)
        if (titulosColunas.length == 2) {
             table.setWidths(new float[]{3f, 2f});
        }

        // 3. Cabeçalho da Tabela
        criarCabecalhoTabela(table, titulosColunas);

        // 4. Dados da Tabela
        escreverDadosTabela(table);

        // 5. Adiciona a tabela ao documento
        document.add(table);
        
        document.close();
    }
}