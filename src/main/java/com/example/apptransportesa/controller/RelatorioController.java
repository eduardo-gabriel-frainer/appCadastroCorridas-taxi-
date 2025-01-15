package com.example.apptransportesa.controller;

import com.example.apptransportesa.model.Corrida;
import com.example.apptransportesa.services.CorridaService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class RelatorioController {

    @Autowired
    private CorridaService corridaService;

    @GetMapping("/gerar-relatorio-pdf")
    public ResponseEntity<byte[]> gerarRelatorioPdf() throws DocumentException,
            IOException {
        List<Corrida> corridas = corridaService.listarCorridas();

        // Criação do documento
        Document doc = new Document(PageSize.A4, 10, 10, 72, 72);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Criação do PdfWriter
        PdfWriter writer = PdfWriter.getInstance(doc, baos);

        // Abrir o documento
        doc.open();

        // Adicionando título
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph titulo = new Paragraph("Relatório de Corridas", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        doc.add(titulo);

        // Adicionar uma linha em branco
        doc.add(new Paragraph("\n"));

        // Criando a tabela com 7 colunasS
        PdfPTable table = new PdfPTable(7);

        // Definir os títulos das colunas
        table.addCell("Origem");
        table.addCell("Destino");
        table.addCell("Nome User");
        table.addCell("Valor");
        table.addCell("Forma Pagamento");
        table.addCell("Aplicativo");
        table.addCell("Motorista");

        // Preenchendo a tabela com os dados das corridas
        for (Corrida corrida : corridas) {
            table.addCell(corrida.getOrigem());
            table.addCell(corrida.getDestino());
            table.addCell(corrida.getNome_usuario());
            table.addCell(String.valueOf(corrida.getValor()));
            table.addCell(corrida.getForma_pagamento());
            table.addCell(corrida.getAplicativo() != null ? corrida.getAplicativo().getNome() : "N/A");
            table.addCell(corrida.getMotorista() != null ? corrida.getMotorista().getNome() : "N/A");
        }

        // Adicionando a tabela ao documento
        doc.add(table);

        // Fechar o documento
        doc.close();

        // Retornar o PDF como resposta
        byte[] pdfBytes = baos.toByteArray();

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "attachment; filename=relatorio_corridas.pdf")
                .body(pdfBytes);
    }
}