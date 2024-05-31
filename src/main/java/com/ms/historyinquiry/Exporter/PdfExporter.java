package com.ms.historyinquiry.Exporter;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.ms.historyinquiry.Model.Vaccine;
import org.springframework.stereotype.Component;

import javax.swing.text.StyleConstants;

import static com.itextpdf.text.Font.FontFamily.HELVETICA;

@Component
public class PdfExporter {

    public static byte[] exportToPdf(List<Vaccine> vaccinationHistory) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);

            Document document = new Document(new PdfDocument(writer), PageSize.A4);
            document.setFont(font);

            // Título del documento
            document.add(new Paragraph("Vacunit Lista de vacunas aplicadas"));

            // Crear tabla para mostrar las vacunas
            Table table = new Table(3);
            table.addHeaderCell("Vacuna");
            table.addHeaderCell("Fabricante");

            // Agregar las vacunas a la tabla
            for (Vaccine vaccine : vaccinationHistory) {
                table.addCell(vaccine.getName());
                table.addCell(vaccine.getManufacturer());
            }

            // Agregar la tabla al documento
            document.add(table);
            document.close();

            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
