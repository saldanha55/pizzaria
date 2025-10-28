package com.mycompany.pizzariaweb.servico;

import com.mycompany.pizzariaweb.modelo.dto.RelatorioDTO;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExportService {

    /**
     * Gera uma planilha Excel (.xlsx) a partir de um RelatorioDTO e a envia
     * diretamente para o HttpServletResponse.
     *
     * @param response       O HttpServletResponse para onde a planilha será escrita.
     * @param dto            O DTO contendo os dados (labels = coluna 1, values = coluna 2).
     * @param nomeArquivo    O nome do arquivo (ex: "relatorio_clientes.xlsx").
     * @param nomePlanilha   O nome da aba da planilha (ex: "Clientes").
     * @param titulosColunas Um array de String com os títulos (ex: ["Bairro", "Total"]).
     */
    public void gerarPlanilha(HttpServletResponse response, RelatorioDTO dto, String nomeArquivo, String nomePlanilha, String[] titulosColunas) throws IOException {

        try (Workbook workbook = new XSSFWorkbook(); OutputStream out = response.getOutputStream()) {

            Sheet sheet = workbook.createSheet(nomePlanilha);

            // 1. Criar Estilo do Cabeçalho
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // 2. Criar Linha do Cabeçalho
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < titulosColunas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(titulosColunas[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // 3. Preencher os Dados
            List<String> labels = dto.getLabels();
            List<Object> values = dto.getValues();
            int rowNum = 1;

            for (int i = 0; i < labels.size(); i++) {
                Row row = sheet.createRow(rowNum++);

                // Coluna 1 (Label)
                row.createCell(0).setCellValue(labels.get(i));

                // Coluna 2 (Value)
                Cell valueCell = row.createCell(1);
                Object value = values.get(i);

                // Trata números de forma diferente de texto
                if (value instanceof Number) {
                    valueCell.setCellValue(((Number) value).doubleValue());
                } else {
                    valueCell.setCellValue(value.toString());
                }
            }

            // 4. Auto-ajustar o tamanho das colunas
            for (int i = 0; i < titulosColunas.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // 5. Configurar a Resposta HTTP
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo);

            // 6. Escrever o workbook no OutputStream
            workbook.write(out);

        } catch (Exception e) {
            throw new IOException("Erro ao gerar planilha Excel: " + e.getMessage(), e);
        }
    }
}