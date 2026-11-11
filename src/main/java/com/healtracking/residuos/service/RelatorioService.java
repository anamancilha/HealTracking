package com.healtracking.residuos.service;

import com.healtracking.residuos.model.Residuo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class RelatorioService {

    // 1. MÉTODO PARA GERAR O PDF
    public void exportarResiduosPDF(HttpServletResponse response, List<Residuo> residuos) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Título do Documento
        Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.DARK_GRAY);
        Paragraph titulo = new Paragraph("Relatório de Rastreabilidade de Resíduos Hospitalares", fontTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setSpacingAfter(20);
        document.add(titulo);

        // Criando a Tabela (5 colunas)
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.0f, 3.0f, 2.5f, 1.5f, 2.0f});

        // Cabeçalho da Tabela
        String[] cabecalhos = {"ID", "Gerador", "Tipo de Resíduo", "Peso (Kg)", "Status"};
        Font fontCabecalho = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);

        for (String col : cabecalhos) {
            PdfPCell cell = new PdfPCell(new Phrase(col, fontCabecalho));
            cell.setBackgroundColor(new Color(25, 135, 84)); // Verde Bootstrap
            cell.setPadding(6);
            table.addCell(cell);
        }

        // Preenchendo as linhas com os dados do banco
        for (Residuo r : residuos) {
            table.addCell(String.valueOf(r.getIdResiduo()));
            table.addCell(r.getGerador().getRazaoSocial());
            table.addCell(r.getTipoResiduo().getGrupoClassificacao());
            table.addCell(String.valueOf(r.getPesoKg()));
            table.addCell(r.getStatus());
        }

        document.add(table);
        document.close();
    }

    // 2. MÉTODO PARA GERAR O CSV (EXCEL)
    public void exportarResiduosCSV(PrintWriter writer, List<Residuo> residuos) throws IOException {
        // Usando ponto e vírgula (;) como delimitador para abrir direto nas colunas do Excel em português
        CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("ID", "Estabelecimento Gerador", "Tipo de Residuo", "Data Geracao", "Peso (Kg)", "Status")
                .setDelimiter(';')
                .build();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, format)) {
            for (Residuo r : residuos) {
                csvPrinter.printRecord(
                        r.getIdResiduo(),
                        r.getGerador().getRazaoSocial(),
                        r.getTipoResiduo().getNomeTipo(),
                        r.getDataGeracao(),
                        r.getPesoKg(),
                        r.getStatus()
                );
            }
        }
    }
}
